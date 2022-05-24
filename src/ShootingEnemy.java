public class ShootingEnemy extends BasicEnemy{

    protected int side;
    protected int  projectileDamage;
    public int reloadTime = 240;
    public int reload;

    public ShootingEnemy(int x, int y, int width, int height, ID id, Handler handler, int movingrange, int damage,int projectileDamage,int side) {
        super(x, y, width, height, id, handler, movingrange, damage);
        this.side = side;
        this.projectileDamage = projectileDamage;
    }

    @Override
    public void tick(){
        x += velX;
        y += velY;
        falling = true;
        if(jumping || falling){

            if(velY < 10){
                velY += gravity;
            }
        }
        collision();
        shoot();
    }

    public void shoot(){
        if(reload <= 0){
            if(side >0){
                Projectile projectile = new Projectile(x + width,y,32,16,ID.Projectile,handler,projectileDamage,side);
                reload = reloadTime;
                handler.addObject(projectile);
            }
            else {
                Projectile projectile = new Projectile(x -32,y,32,16,ID.Projectile,handler,projectileDamage,side);
                reload = reloadTime;
                handler.addObject(projectile);
            }
        }
        else {
            reload -= 1;
        }
    }

}
