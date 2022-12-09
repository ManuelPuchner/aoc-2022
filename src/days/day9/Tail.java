package days.day9;

public class Tail extends Node {

    private Node nodeToFollow;
    private int tailNumber;

    public Tail(Node nodeToFollow, int tailNumber) {
        this.nodeToFollow = nodeToFollow;
        this.tailNumber = tailNumber;
    }

    public boolean isDiagonal() {
        return (nodeToFollow.x - 1 == this.x && nodeToFollow.y - 1 == this.y) ||
                (nodeToFollow.x + 1 == this.x && nodeToFollow.y - 1 == this.y) ||
                (nodeToFollow.x - 1 == this.x && nodeToFollow.y + 1 == this.y) ||
                (nodeToFollow.x + 1 == this.x && nodeToFollow.y + 1 == this.y);
    }

    public boolean isDirectNeighbor() {
        return (nodeToFollow.x == this.x && nodeToFollow.y - 1 == this.y) ||
                (nodeToFollow.x == this.x && nodeToFollow.y + 1 == this.y) ||
                (nodeToFollow.x - 1 == this.x && nodeToFollow.y == this.y) ||
                (nodeToFollow.x + 1 == this.x && nodeToFollow.y == this.y);
    }

    private boolean isOverLapping() {
        return nodeToFollow.x == this.x && nodeToFollow.y == this.y;
    }

    public void follow(Direction direction) {
        if (!(isDiagonal() || isDirectNeighbor() || isOverLapping())) {
            this.lastX = this.x;
            this.lastY = this.y;
            this.x = nodeToFollow.lastX;
            this.y = nodeToFollow.lastY;

            if (isDiagonal()) {
                if(direction == Direction.UP && this.lastY + 1 == nodeToFollow.y) {
                    y++;
                }
                if(direction == Direction.DOWN) {
                    y--;
                }
                if(direction == Direction.LEFT) {
                    x--;
                }
                if(direction == Direction.RIGHT) {
                    x++;
                }
            }

        }

        this.lastDirection = direction;
    }

    public int getTailNumber() {
        return tailNumber;
    }
}
