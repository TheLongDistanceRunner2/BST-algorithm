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
    public Node insertRec(Node n, int value) {
        // jeśli nasz obecny wierzchołek jest pusty (jest liściem lub korzeniem), tworzymy nowy wierzchołek,
        // wpisując w niego naszą wartość:
        if (n == null) {
            n = new Node(value);
            return n;
        }

        // jeśli wstawiana wartość jest mniejsza, idziemy w lewą gałąż:
        if (value < n.getValue()) {
            // i wywołujemy rekurencyjnie naszą funkcje ze "wskaźnikiem" ustawionym na lewą gałąź:
            n.left = insertRec(n.left, value);
        }
        // jeśli wstawiana wartość jest równa lub większa, idziemy w prawo:
        else if (value >= n.getValue()) {
            // i wywołujemy rekurencyjnie naszą funkcje ze "wskaźnikiem" ustawionym na prawą gałąź:
            n.right = insertRec(n.right, value);
        }

        // i zwracamy "wskaznik" na nasz obecny wierzchołek:
        return n;
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
            System.out.print(root.getValue() + " ");
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

//        for (int i = 0; i < ; i++) {
//
//
//        }

        return array;
    }

    public int findNodeOfValue(Node n,int value) {
        // gdy nie ma wartości, zwrazacamy -1:
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

    public void printTreeRec(Node n, int spaces) {
        if (n == null) {
            return;
        }

        spaces += 10;

        printTreeRec(n.right, spaces);

        System.out.println("\n");
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }

        System.out.print(n.getValue());

        boolean flag = true;
        boolean flag2 = true;

        if(n.left != null) {
            flag = false;

            if(n.right != null) {
                System.out.print("<");
                flag2 = false;
            }
            else {
                System.out.print("\\");
            }
        }

        if(n.right != null && flag && flag2) {
            System.out.print("/");
        }

        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }

        printTreeRec(n.left, spaces);
    }

    public void printTree() {
        int spaces = 1;
        printTreeRec(node, spaces);
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

        int minValue = tree.findMinValue();
        System.out.println("Wartość minimalna drzewa to: " + minValue);
        int numberMinFindOperations = tree.getNumberMinFindOperations();
        System.out.println("Ilość operacji szukania min: " + numberMinFindOperations);

        int maxValue = tree.findMaxValue();
        System.out.println("\nWartość maksymalna drzewa to: " + maxValue);
        int numberMaxFindOperations = tree.getNumberMaxFindOperations();
        System.out.println("Ilość operacji szukania max: " + numberMaxFindOperations + "\n");

        int numberOfElements = tree.inorder();
        System.out.println("\nIlość elementów drzewa: " + numberOfElements);

        System.out.println("\n\n\n");
        tree.printTree();
    }
}
