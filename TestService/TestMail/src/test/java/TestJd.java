    public class TestJd {
        public static void main(String[] args) {
            MyThread.Node list1 = null;
            MyThread.Node list2 = null;


            new Thread(new MyThread(list1, list2)).start();
        }

    }

class MyThread implements Runnable {
    private Node listX;
    private Node listY;

    public MyThread(Node x, Node y) {
        this.listX = x;
        this.listY = y;
    }

    @Override
    public void run() {
        Node currentX = listX;
        Node currentY = listY;
        int length = 0;
        while (true) {
            if (currentX == null ) {
                break;
            }
            if (getLength(currentX) > getLength(currentY)) {
                break;
            }
            for (int i = 0; i < 10; i++) {

            }
            break;
        }

    }


    private int getLength(Node myList) {
            int count = 0;
        while (myList != null && myList.next != null){
            count ++;
        }
        return count;
    }

    class Node {
        public Node(String name) {
            this.name = name;
            this.next = null;
        }

        private String name;
        private Node next;
    }
}


