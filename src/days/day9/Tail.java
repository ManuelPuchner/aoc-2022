package days.day9;

public class Tail extends Node {

    private Node nodeToFollow;

    public Tail(Node nodeToFollow) {
        this.nodeToFollow = nodeToFollow;
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
            this.x = nodeToFollow.lastX;
            this.y = nodeToFollow.lastY;
        }
        this.lastDirection = direction;
    }
}
