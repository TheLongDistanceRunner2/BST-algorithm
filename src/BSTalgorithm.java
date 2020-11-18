import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

// https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
public class BSTalgorithm {
    private Node node;
    private File file;
    private int numberOfElements;

    private int numberMinFindOperations;
    private int numberMaxFindOperations;


    public BSTalgorithm() throws IOException {
        node = null;
        file = readFile("input.txt");
        numberOfElements = 0;
        numberMinFindOperations = 0;
        numberMaxFindOperations = 0;
        //showFile(file);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getNumberMinFindOperations() {
        return numberMinFindOperations;
    }

    public void setNumberMinFindOperations(int numberMinFindOperations) {
        this.numberMinFindOperations = numberMinFindOperations;
    }

    public int getNumberMaxFindOperations() {
        return numberMaxFindOperations;
    }

    public void setNumberMaxFindOperations(int numberMaxFindOperations) {
        this.numberMaxFindOperations = numberMaxFindOperations;
    }

    //==================================================================================================================

    // This method mainly calls insertRec()
    public void insert(int key) {
        node = insertRec(node, key);
    }

    // https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
    // https://www.tutorialspoint.com/binary-search-tree-insert-with-parent-pointer-in-cplusplus
    public Node insertRec(Node node, int value) {
        // jeśli nasz obecny wierzchołek jest pusty (jest liściem lub korzeniem), tworzymy nowy wierzchołek,
        // wpisując w niego naszą wartość:
        if (node == null) {
            return new Node(value);
        }

        // jeśli wstawiana wartość jest mniejsza, idziemy w lewą gałąż:
        if (value < node.getValue()) {
            // i wywołujemy rekurencyjnie naszą funkcje ze "wskaźnikiem" ustawionym na lewą gałąź:
            Node lchild = insertRec(node.left, value);
            node.left = lchild;

            // ustawiamy rodzica:
            lchild.parent = node;
        }
        // jeśli wstawiana wartość jest równa lub większa, idziemy w prawo:
        else if (value > node.getValue()) {
            // i wywołujemy rekurencyjnie naszą funkcje ze "wskaźnikiem" ustawionym na prawą gałąź:
            Node rchild = insertRec(node.right, value);
            node.right = rchild;

            // ustawiamy rodzica:
            rchild.parent = node;
        }

        // i zwracamy "wskaznik" na nasz obecny wierzchołek:
        return node;
    }

    // This method mainly calls InorderRec()
    public int inorder() {
        return inorderRec(node);
    }

    // wyświetl drzewo w kolejności in-order:
    public int inorderRec(Node root) {
        if (root != null) {
            this.numberOfElements++;

            inorderRec(root.left);
            System.out.print("Wezeł: : "+ root.value + " , ");
            if (root.parent == null)
                System.out.println("Rodzic : NULL");
            else
                System.out.println("Rodzic : " + root.parent.value);
            inorderRec(root.right);
        }
        return numberOfElements;
    }

    //https://www.educative.io/edpresso/finding-the-maximum-depth-of-a-binary-tree
    // Dzięki rekurencyjnemu wywołaniu, funkcja na każdym poziomie porówna ilość (potencjalnych) potomków rodzica
    // w lewej i prawę gałęzi, idąc od korzenia w dół:
    public int findDepthPreOrderRec(Node root) {
        // jeśli korzeń jest pusty, to drzewo ma zerową wysokość:
        if(root == null) {
            return 0;
        }

        // rekurencyjnie wywołujemy funkcję, przechodząc najpierw w lewą gałąź, a potem w prawą:
        int leftDepth = findDepthPreOrderRec(root.left);
        int rightDepth = findDepthPreOrderRec(root.right);

        // Porównujemy, przejściem której gałęzi napotkamy więcej potomków.
        // Wysokością drzewa będzie ta wartość powiększona o 1 - dodajemy do wyniku poziom korzenia:
        if (leftDepth > rightDepth)
            return (1 + leftDepth);
        else
            return (1 + rightDepth);
    }

    public int findDepthPreOrder() {
        return findDepthPreOrderRec(node);
    }

    public ArrayList drawNumbers(int numberForGenerator, int min, int max) {
        ArrayList<Integer> array = new ArrayList();

        int i = 0;
        while (i < numberForGenerator) {
            int tmp = ThreadLocalRandom.current().nextInt(min, max + 1);
            //System.out.println(tmp);
            if (!array.contains(tmp)) {
                array.add(tmp);
                i++;
            }
        }

        return array;
    }

    public int findNodeOfValue(Node n,int value) {
        // gdy nie ma wartości, zwracamy -1:
        if (n == null) {
            return -1;
        }

        if (value == n.getValue()) {
            return value;
        }

        if (value < n.getValue()) {
            return findNodeOfValue(n.left, value);
        }
        else {
            return findNodeOfValue(n.right, value);
        }
    }

    public int findNode(int value) {
        return findNodeOfValue(this.node, value);
    }

    public int findMinValueRec(Node n, int x) {
        this.numberMinFindOperations++;

        if (n != null) {
            x = n.getValue();

            return findMinValueRec(n.left, x);
        }
        else {
            if (this.numberMinFindOperations != 1) {
                this.numberMinFindOperations--;
            }
            return x;
        }
    }

    public int findMinValue() {
        int x = 0;
        return findMinValueRec(node, x);
    }

    public int findMaxValueRec(Node n, int x) {
        this.numberMaxFindOperations++;

        if (n != null) {
            x = n.getValue();

            return findMaxValueRec(n.right, x);
        }
        else {
            if (this.numberMaxFindOperations != 1) {
                this.numberMaxFindOperations--;
            }
            return x;
        }
    }

    public int findMaxValue() {
        int x = 0;
        return findMaxValueRec(node, x);
    }

    //https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
    public void printTreeRec(Node n, int spaces) {
        // jeśli węzeł jest nullem, to kończymy:
        if (n == null) {
            return;
        }

        // dodajemy ilość spacji:
        spaces += 10;

        // wywołujemy rekurencyjnie prawą gałąź:
        printTreeRec(n.right, spaces);

        // wyświetlamy odpowiednią ilość spacji linię poniżej, w miarę "oddalania się" od korzenia:
        System.out.println("\n");
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }

        // wyświetlamy wartość węzła:
        System.out.print(n.getValue());

        boolean flag = true;
        boolean flag2 = true;

        // jeśli jest lewy potomek:
        if(n.left != null) {
            // przestawiamy pierwszą flagę:
            flag = false;

            // jesli jest prawy potomek:
            if(n.right != null) {
                // wyświetlamy znak '<', świadczący o dwóch potomkach:
                System.out.print("<");
                // przestawiamy drugą flagę:
                flag2 = false;
            }
            // jeśli nie ma prawego potomka:
            else {
                // wyświetlamy znak '\', świaeczący o istnieniu tylko lewego potomka:
                System.out.print("\\");
            }
        }

        // jeśli prawy potomek istnieje i dwie flagi mają wartość True:
        if(n.right != null && flag && flag2) {
            // wyświetlamy znak '/', świadczący o istnieniu tylko prawego potomka
            System.out.print("/");
        }

        // wyświetlamy odpowienią ilość spacji w miarę "oddalania się" od korzenia:
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }

        // wywołujemy rekurencyjnie lewą gałąź:
        printTreeRec(n.left, spaces);
    }

    public void printTree() {
        int spaces = 1;
        printTreeRec(node, spaces);
    }

    // https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
    // https://www.java2novice.com/java-interview-programs/delete-node-binary-search-tree-bst/
    public Node deleteNodeRec(Node root, int value) {
        // jeśli węzeł jest nullem, to kończymy:
        if (root == null) {
            return root;
        }

        // wywołujemy rekurencyjnie lewą gałąź:
        if (value < root.value) {
            root.left = deleteNodeRec(root.left, value);
        }
        // wywołujemy rekurencyjnie prawą gałąź:
        else if (value > root.value) {
            root.right = deleteNodeRec(root.right, value);
        }
        // gdy znaleziono element do usunięcia:
        else {
            // sprawdzamy czy węzeł ma jakichś potomków:
            // jeśli nie ma żadnego potomka, to usuwamy:
            if(root.left == null && root.right == null) {
                return null;
            }
            // jesli istnieje tylko prawy potomek:
            else if(root.left == null) {
                // korzeniowi przypisujemy wartość z prawego potomka, a potomka usuwamy:
                return root.right;
            }
            // jesli istnieje tylko lewy potomek:
            else if(root.right == null) {
                // korzeniowi przypisujemy wartość z lewego potomka, a potomka usuwamy:
                return root.left;
            }
            // jesli istnieją dwaj potomkowie:
            else {
                // szukamy w prawej gałęzi potomka o najmniejszej wartości:
                Integer minValue = minValue(root.right);
                // przypisujemy jego wartość do naszego korzenia:
                root.setValue(minValue);
                // ... i usuwamy potomka z prawej gałęzi:
                root.right = deleteNodeRec(root.right, root.value);
            }
        }

        return root;
    }

    private int minValue(Node root) {
        int min = root.value;

        // przeszukujemy prawą gałąź:
        while (root.left != null) {
            min = root.left.value;
            root = root.left;
        }

        return min;
    }

    public void deleteNode(int value) {
        node = deleteNodeRec(node, value);
    }

    public void deleteNodes(ArrayList<Integer> tab) {
        System.out.println("Usunięto z drzewa: ");
        // usuwamy wylosowane wartości w kolejności dodawania ich do tablicy:
        for (int i = 0; i < file.getNumberOfElementsToDelte(); i++) {
            System.out.println(this.findNode(tab.get(i)));
            this.deleteNode(tab.get(i));
        }
    }

    private Node findNodeToRotate(Node n,int value) {
        // gdy nie ma wartości, zwracamy wierzchołek z wartością -1:
        if (n == null) {
            return new Node(-1);
        }

        // gdy znaleziono nasz wierzchołek, zwracamy go:
        if (value == n.getValue()) {
            return n;
        }

        // jeśli szukana wartość jest mniejsza, przechodzimy do lewej gałęzi:
        if (value < n.getValue()) {
            return findNodeToRotate(n.left, value);
        }
        else {
            return findNodeToRotate(n.right, value);
        }
    }

    private Node findParentRec(Node n, int value, int parent) {
        // gdy nie ma wartości, zwracamy wierzchołek z wartością -1:
        if (n == null) {
            return new Node(-1);
        }

        // gdy znaleziono nasz wierzchołek, zwracamy go:
        if (value == n.getValue()) {
            return new Node(parent);
        }
        else {
            if (value < n.getValue()) {
                return findParentRec(n.left, value, n.value);
            }
            else {
                return findParentRec(n.right, value, n.value);
            }
        }
    }

    public Node findParent(int value) {
        return findParentRec(node, value, -1);
    }

    // https://cs.lmu.edu/~ray/notes/binarysearchtrees/
    // https://eduinf.waw.pl/inf/alg/001_search/0116.php
    public void rotateR(int value) {
        // znajdujemy węzeł z oczekiwaną wartością:
        Node A = findNodeToRotate(node, value);
        // tworzymy B, czyli odniesienie do starego potomka A:
        Node B = A.getLeft();

        // jesli nasz węzeł A nie ma lewego potomka, to przerywamy,
        // bo w takim wypadku nie można wykonać rotacji:
        if (B == null) {
            System.out.println("Rotacja nie mogła zostać wykonana - brak lewego potomka!");
            return;
        }

        // wskazanie ojca A:
        Node p = A.getParent();

        // lewym synem A staje się prawy syn B:
        A.setLeft(B.getRight());

        // sprawdzamy, czy p był korzeniem:
        if (p == null) {
            // jeśli tak, to korzeniem jest teraz B:
            node = B;
        }
        // jesli nie, to:
        // jeśli A było lewym synem swojego rodzica, to:
        else if (p.getLeft() == A) {
            // lewym synem p jest teraz B:
            p.setLeft(B);
        }
        else {
            // prawym synem p jest teraz B:
            p.setRight(B);
        }

        // prawym synem B jest teraz A:
        B.setRight(A);
    }

    // https://cs.lmu.edu/~ray/notes/binarysearchtrees/
    // https://eduinf.waw.pl/inf/alg/001_search/0116.php
    public void rotateL(int value) {
        // znajdujemy węzeł z oczekiwaną wartością:
        Node A = findNodeToRotate(node, value);
        // tworzymy B, czyli odniesienie do starego potomka A:
        Node B = A.getRight();

        // jesli nasz węzeł A nie ma prawego potomka, to przerywamy,
        // bo w takim wypadku nie można wykonać rotacji:
        if (B == null) {
            System.out.println("Rotacja nie mogła zostać wykonana - brak prawego potomka!");
            return;
        }

        // wskazanie ojca A:
        Node p = A.getParent();

        // prawym synem A staje się lewy syn B:
        A.setRight(B.getLeft());

        // sprawdzamy, czy p był korzeniem:
        if (p == null) {
            // jeśli tak, to korzeniem jest teraz B:
            node = B;
        }
        // jesli nie, to:
        // jeśli A było lewym synem swojego rodzica, to:
        else if (p.getLeft() == A) {
            // lewym synem p jest teraz B:
            p.setLeft(B);
        }
        else {
            // prawym synem p jest teraz B:
            p.setRight(B);
        }

        // lewym synem B jest teraz A:
        B.setLeft(A);
    }


    //==================================================================================================================
    private File readFile(String file_name) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        File file = new File();

        try {
            inputStream = new FileInputStream(file_name);
            sc = new Scanner(inputStream, "UTF-8");

            file.setNumberForGenerator(sc.nextInt());
            file.setNumberOfElementsToAdd(sc.nextInt());
            file.setNumberOfElementsToDelte(sc.nextInt());
            file.setNodeToRotate1(sc.nextInt());
            file.setRotation1(sc.next().charAt(0));
            file.setNodeToRotate2(sc.nextInt());
            file.setRotation2(sc.next().charAt(0));

            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        return file;
    }

    private void showFile(File file) {
        System.out.println("Number of tree elements: " + file.getNumberForGenerator());
        System.out.println("Number of elements to add: " + file.getNumberOfElementsToAdd());
        System.out.println("Number of elements do delete: " + file.getNumberOfElementsToDelte());
        System.out.println("Number of node to rotate 1: " + file.getNodeToRotate1());
        System.out.println("Rotation 1: " + file.getRotation1());
        System.out.println("Number of node to rotate 2: " + file.getNodeToRotate2());
        System.out.println("Rotation 2: " + file.getRotation2());
    }

    public static void main(String[] args) throws IOException {
        BSTalgorithm tree = new BSTalgorithm();
        int numberForGenerator = tree.getFile().getNumberForGenerator();
        ArrayList<Integer> tab = tree.drawNumbers(numberForGenerator, 1, 100);

        int elementsToAdd = tree.getFile().getNumberOfElementsToAdd();

        // dodajemy wylosowane wartości do drzewa:
        for (int i = 0; i < elementsToAdd; i++) {
            System.out.println(tab.get(i));
            tree.insert(tab.get(i));
        }

        //int elementsNumber = tree.inorder();
        //System.out.println("Number of elements in tree: " + elementsNumber);

        int depth = tree.findDepthPreOrder();
        System.out.println("\nHeight of tree: " + depth);

//        System.out.println("Podaj wartość węzła, która chcesz znaleźć: \n" );
//        int input = 0;
//        Scanner scanner = new Scanner(System.in);
//        input = scanner.nextInt();
//
//        System.out.println("W drzewie znajudje się: " + tree.findNode(input));

//        int minValue = tree.findMinValue();
//        System.out.println("Wartość minimalna drzewa to: " + minValue);
//        int numberMinFindOperations = tree.getNumberMinFindOperations();
//        System.out.println("Ilość operacji szukania min: " + numberMinFindOperations);
//
//        int maxValue = tree.findMaxValue();
//        System.out.println("\nWartość maksymalna drzewa to: " + maxValue);
//        int numberMaxFindOperations = tree.getNumberMaxFindOperations();
//        System.out.println("Ilość operacji szukania max: " + numberMaxFindOperations + "\n");
//
//        int numberOfElements = tree.inorder();
//        System.out.println("\nIlość elementów drzewa: " + numberOfElements);

        //System.out.println("\n\n\n");
        //tree.printTree();
        //tree.inorder();

//        System.out.println("\n\n\n");
//        tree.deleteNodes(tab);
//
//        System.out.println("\n\n\n");
//        tree.printTree();
        tree.inorder();
        tree.printTree();

        System.out.println("\n\n\n\n\nRotacja względem węzła: " + tab.get(5) + "\n\n\n\n");
        tree.rotateL(tab.get(5));
//
//        System.out.println("\n\n\n");
//        //tree.printTree();
        tree.inorder();
        tree.printTree();

//        System.out.println("rodzic "+ tab.get(5) + " to:" + tree.findParent(tab.get(5)).value);
//        System.out.println("rodzic "+ tab.get(10) + " to:" + tree.findParent(tab.get(10)).value);
//        System.out.println("rodzic "+ tab.get(4) + " to:" + tree.findParent(tab.get(4)).value);
//        System.out.println("rodzic "+ tab.get(1) + " to:" + tree.findParent(tab.get(1)).value);
    }
}
