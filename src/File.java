public class File {
    private int numberForGenerator;
    private int numberOfElementsToAdd;
    private int numberOfElementsToDelte;
    private int nodeToRotate1;
    private char rotation1;
    private int nodeToRotate2;
    private char rotation2;

    public File() {
        this.numberForGenerator = 0;
        this.numberOfElementsToAdd = 0;
        this.numberOfElementsToDelte = 0;
        this.nodeToRotate1 = 0;
        this.rotation1 = ' ';
        this.nodeToRotate2 = 0;
        this.rotation2 = ' ';
    }

    public int getNumberForGenerator() {
        return numberForGenerator;
    }

    public void setNumberForGenerator(int numberForGenerator) {
        this.numberForGenerator = numberForGenerator;
    }

    public int getNumberOfElementsToAdd() {
        return numberOfElementsToAdd;
    }

    public void setNumberOfElementsToAdd(int numberOfElementsToAdd) {
        this.numberOfElementsToAdd = numberOfElementsToAdd;
    }

    public int getNumberOfElementsToDelte() {
        return numberOfElementsToDelte;
    }

    public void setNumberOfElementsToDelte(int numberOfElementsToDelte) {
        this.numberOfElementsToDelte = numberOfElementsToDelte;
    }

    public int getNodeToRotate1() {
        return nodeToRotate1;
    }

    public void setNodeToRotate1(int nodeToRotate1) {
        this.nodeToRotate1 = nodeToRotate1;
    }

    public char getRotation1() {
        return rotation1;
    }

    public void setRotation1(char rotation1) {
        this.rotation1 = rotation1;
    }

    public int getNodeToRotate2() {
        return nodeToRotate2;
    }

    public void setNodeToRotate2(int nodeToRotate2) {
        this.nodeToRotate2 = nodeToRotate2;
    }

    public char getRotation2() {
        return rotation2;
    }

    public void setRotation2(char rotation2) {
        this.rotation2 = rotation2;
    }
}
