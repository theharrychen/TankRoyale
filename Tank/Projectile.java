public class Projectile {

    private String name;
    private int speed;
    private float damage;
    private int reloadTime;

    private int x;
    private int y;

    public Projectile(String name, int speed, float damage, int reloadTime)
    {
        setName(name);
        setSpeed(speed);
        setDamage(damage);
        setReloadTime(reloadTime);
        this.x = 0;
        this.y = 0;
    }

    public void fire(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    public void setDamage(float damage)
    {
        if (damage < 0.0f)
        {
            this.damage = 0.0f;
        }
        this.damage = damage;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getDamage()
    {
        return damage;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getReloadTime() {
        return reloadTime;
    }

}
