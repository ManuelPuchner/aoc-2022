package days.day9;

public class Tail extends Node {

    private Node nodeToFollow = null;

    public Tail(Node nodeToFollow) {
        this.nodeToFollow = nodeToFollow;
    }

    public boolean isDiagonal() {
        boolean isDiagonal = false;

        if(nodeToFollow.x - 1 == this.x && nodeToFollow.y - 1 == this.y) {
            isDiagonal = true;
        }
        if(nodeToFollow.x + 1 == this.x && nodeToFollow.y - 1 == this.y) {
            isDiagonal = true;
        }
        if(nodeToFollow.x - 1 == this.x && nodeToFollow.y + 1 == this.y) {
            isDiagonal = true;
        }
        if(nodeToFollow.x + 1 == this.x && nodeToFollow.y + 1 == this.y) {
            isDiagonal = true;
        }

        return isDiagonal;
    }

    public boolean isDirectNeighbor() {
        boolean isDirectNeighbor = false;

        if(nodeToFollow.x == this.x && nodeToFollow.y - 1 == this.y) {
            isDirectNeighbor = true;
        }
        if(nodeToFollow.x == this.x && nodeToFollow.y + 1 == this.y) {
            isDirectNeighbor = true;
        }
        if(nodeToFollow.x - 1 == this.x && nodeToFollow.y == this.y) {
            isDirectNeighbor = true;
        }
        if(nodeToFollow.x + 1 == this.x && nodeToFollow.y == this.y) {
            isDirectNeighbor = true;
        }


        return isDirectNeighbor;

    }

    private boolean isOverLapping() {
        return nodeToFollow.x == this.x && nodeToFollow.y == this.y;
    }

    public void follow(Direction direction) {
        if(!(isDiagonal() || isDirectNeighbor() || isOverLapping())) {
            this.x = nodeToFollow.lastX;
            this.y = nodeToFollow.lastY;
        }
        this.lastDirection = direction;
    }
}
