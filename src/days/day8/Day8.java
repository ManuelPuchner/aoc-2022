package days.day8;

import days.Day;

import java.util.ArrayList;
import java.util.List;

public class Day8 extends Day {
    List<List<Tree>> trees;

    public Day8() {
        trees = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split("");
            List<Tree> tree = new ArrayList<>();
            for (int j = 0; j < parts.length; j++) {
                tree.add(new Tree(Integer.parseInt(parts[j]), j, i));
            }
            trees.add(tree);
        }
    }

    static class Tree {
        int height;
        int x;
        int y;
        boolean isVisible = false;

        public Tree(int height, int x, int y) {
            this.height = height;
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public Object solvePart2() {
        int biggestScore = 0;
        for (List<Tree> row : trees) {
            for (Tree tree : row) {
                int scenicScore = getScenicScore(tree);
                if (scenicScore > biggestScore) {
                    biggestScore = scenicScore;
                }
            }
        }
        return biggestScore;
    }

    @Override
    public Object solvePart1() {
        int visibleTrees = 0;
        for (int i = 0; i < trees.size(); i++) {
            List<Tree> row = trees.get(i);
            for (int j = 0; j < row.size(); j++) {
                visibilityCheck(row, j);
                List<Tree> column = new ArrayList<>();
                for (List<Tree> treeList : trees) {
                    column.add(treeList.get(j));
                }
                visibilityCheck(column, i);

                if (row.get(j).isVisible) {
                    visibleTrees++;
                }
            }
        }
        return visibleTrees;
    }

    private void visibilityCheck(List<Tree> row, int index) {
        Tree tree = row.get(index);
        List<Tree> before = row.subList(0, index);
        List<Tree> after = row.subList(index + 1, row.size());
        int biggestBefore = before.stream().mapToInt(t -> t.height).max().orElse(0);
        int biggestAfter = after.stream().mapToInt(t -> t.height).max().orElse(0);
        if (before.size() == 0 || after.size() == 0) {
            tree.isVisible = true;
            return;
        }
        if (tree.height > biggestBefore || tree.height > biggestAfter) {
            tree.isVisible = true;
        }
    }

    private int getScenicScore(Tree tree) {
        int score = 1;
        List<Tree> row = trees.get(tree.y);
        List<Tree> column = new ArrayList<>();
        for (List<Tree> treeList : trees) {
            column.add(treeList.get(tree.x));
        }

        List<Tree> north = column.subList(0, tree.y);
        List<Tree> south = column.subList(tree.y + 1, row.size());
        List<Tree> east = row.subList(tree.x + 1, column.size());
        List<Tree> west = row.subList(0, tree.x);

        //reverse west and north
        north = new ArrayList<>(north);
        north.sort((o1, o2) -> o2.y - o1.y);
        west = new ArrayList<>(west);
        west.sort((o1, o2) -> o2.x - o1.x);

        List<List<Tree>> directions = new ArrayList<>(List.of(north, south, east, west));

        for(List<Tree> direction : directions) {
            int tempScore = 0;
            for (Tree t : direction) {
                tempScore++;
                if (t.height >= tree.height) {
                    break;
                }
            }
            score *= tempScore;
        }

        return score;
    }
}
