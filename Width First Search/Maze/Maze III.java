public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    class Coordinate implements Comparable<Coordinate>{
        int x, y, l;
        String step;
        public Coordinate(int x, int y, String step, int l){
            this.x = x;
            this.y = y;
            this.step = step;
            this.l = l;
        }
        public int compareTo(Coordinate p) {
            return p.l == l ? step.compareTo(p.step) : l - p.l;
        }
    }
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    String[] go = {"u", "d", "l", "r"};
    public String findShortestWay(int[][] maze, int[] start, int[] destination) {
        // write your code here
        
        Coordinate[][] length = new Coordinate[maze.length][maze[0].length];
        
        PriorityQueue<Coordinate> queue = new PriorityQueue<Coordinate>();
        queue.offer(new Coordinate(start[0], start[1], "", 0));
        
        while(!queue.isEmpty()){
            Coordinate top = queue.poll();
            if(length[top.x][top.y] != null && length[top.x][top.y].compareTo(top) <= 0) continue;
            length[top.x][top.y] = top;
            for(int i = 0; i < 4; i++){
                int xx = top.x, yy =top.y, l = top.l;
                String step = top.step;
                while(inboundry(xx, yy, maze, destination)){
                    xx += dx[i];
                    yy += dy[i];
                    l++;
                    
                }
                if(xx != destination[0] || yy != destination[1]){
                    xx -= dx[i];
                    yy -= dy[i];
                    l--;
                }
                step = step + go[i];
                queue.offer(new Coordinate(xx, yy, step, l));
            }
            
        }
        return length[destination[0]][destination[1]] == null ? "impossible" : length[destination[0]][destination[1]].step;
        
    }
    
    private boolean inboundry(int x, int y, int[][] maze, int[] destination){
        if(x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) return false;
        if(maze[x][y] != 0) return false;
        if(x == destination[0] && y == destination[1]) return false;
        
        return true;
    }
}