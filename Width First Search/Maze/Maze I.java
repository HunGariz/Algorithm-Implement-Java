public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    class Coordinate{
        int x, y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<Coordinate> queue = new LinkedList<Coordinate>();
        queue.offer(new Coordinate(start[0], start[1]));
        visited[start[0]][start[1]]=true;
        
        while(!queue.isEmpty()){
            Coordinate top = queue.poll();
            for(int i = 0; i < 4; i++){
                int xx = top.x, yy =top.y;
                while(inboundry(xx, yy, maze, visited)){
                    
                    xx+=dx[i];
                    yy+=dy[i];
                }
                xx-=dx[i];
                yy-=dy[i];
                if (visited[xx][yy]) continue;
                visited[xx][yy]=true;
                if(xx == destination[0] && yy == destination[1]) return true;
                queue.offer(new Coordinate(xx, yy));
            }
            
        }
        return false;
        
    }
    
    private boolean inboundry(int x, int y, int[][] maze, boolean[][] visited){
        if(x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) return false;
        if(maze[x][y] != 0) return false;
        
        return true;
    }
}