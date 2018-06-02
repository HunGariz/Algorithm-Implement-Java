public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    class Coordinate{
        int x, y, step;
        public Coordinate(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // write your code here
        //boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] length = new int[maze.length][maze[0].length];
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                length[i][j] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Coordinate> queue = new PriorityQueue<Coordinate>((o1, o2)->o1.step - o2.step);
        queue.offer(new Coordinate(start[0], start[1], 0));
        //visited[start[0]][start[1]] = true;
        int min = Integer.MAX_VALUE;
        
        while(!queue.isEmpty()){
            Coordinate top = queue.poll();
            if(length[top.x][top.y] <= top.step) continue;
            length[top.x][top.y] = top.step;
            for(int i = 0; i < 4; i++){
                int xx = top.x, yy =top.y, step = top.step;;
                while(inboundry(xx, yy, maze)){
                    xx += dx[i];
                    yy += dy[i];
                    step++;
                }
                xx -= dx[i];
                yy -= dy[i];
                step--;
                // if (visited[xx][yy]) continue;
                // visited[xx][yy]=true;
                //if(xx == destination[0] && yy == destination[1]) min = Math.min(min, step);
                queue.offer(new Coordinate(xx, yy, step));
            }
            
        }
        return length[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : length[destination[0]][destination[1]];
        
    }
    
    private boolean inboundry(int x, int y, int[][] maze){
        if(x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) return false;
        if(maze[x][y] != 0) return false;
        
        return true;
    }
}