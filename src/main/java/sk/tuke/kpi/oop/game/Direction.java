package sk.tuke.kpi.oop.game;



public enum Direction {
    NORTH(0,1), EAST(1,0), SOUTH(0,-1), WEST(-1,0),
    NORTHWEST(-1,1), NORTHEAST(1,1), SOUTHWEST(-1,-1), SOUTHEAST(1,-1), NONE(0,0);

    private int dx;
    private int dy;
    private float angle;


    Direction(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
        this.angle = 0;

    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public float getAngle(){
        /*float angle=0.0f;
        if(this.dx == 0 && this.dy == 1){
            angle = 0.0f;
        }
        else if(this.dx == 1 && this.dy == 0){
            angle = 270.0f;
        }
        else if(this.dx == 0 && this.dy == -1){
            angle = 180.0f;
        }
        else if(this.dx == -1 && this.dy == 0){
            angle = 90.0f;
        }

        else if(this.dx == -1 && this.dy == 1){
            angle = 45.0f;
        }
        else if(this.dx == 1 && this.dy == 1){
            angle = 315.0f;
        }
        else if(this.dx == -1 && this.dy == -1){
            angle = 135.0f;
        }
        else if(this.dx == 1 && this.dy == -1){
            angle = 225.0f;
        }

        this.angle = angle;
        return angle;*/
        switch (this){
            case EAST:
                return 270.0f;
            case NORTH:
                return 0.0f;
            case WEST:
                return 90.0f;
            case SOUTH:
                return 180.0f;
            case NORTHWEST:
                return 45.0f;
            case NORTHEAST:
                return 315.0f;
            case SOUTHWEST:
                return 135.0f;
            case SOUTHEAST:
                return 225.0f;
            default:
                return 0.0f;
        }

    }

    public Direction combine(Direction direction){
       /* if(direction == this || direction == NONE){
            return direction;
        }*/
        int newX =  dx + direction.getDx() ;
        int newY =  dy + direction.getDy() ;

        if(dx == direction.getDx()) newX = dx;
        if(dy == direction.getDy()) newY = dy;

        for(Direction dir : Direction.values()) {
            if (newX == dir.getDx() && newY == dir.getDy()) {
                return dir;
            }
        }
        return direction;
    }

   public static Direction fromAngle(float angle){
       Direction dir = NONE;
       if((float)angle == 0.0f){
           dir = NORTH;
       }else if(angle == 270.0f){
           dir = EAST;
       }else if(angle == 180.0f){
            dir = SOUTH;
       }else if(angle == 90.0f){
            dir = WEST;
       }else if(angle == 45.0f){
            dir = NORTHWEST;
       }else if(angle == 315.0f){
            dir = NORTHEAST;
       }else if(angle == 135.0f){
            dir = SOUTHWEST;
       }else if(angle == 225.0f){
            dir = SOUTHEAST;
       }
       return dir;
   }

}
