public class Wingman extends Projectile {

    public Wingman()
    {
        super("Wingman", 75, 125, 5);
    }

    public Wingman[] disperse()
    {
        Wingman[] wingmen = new Wingman[10];
        return wingmen;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public float getDamage() {
        return super.getDamage();
    }

    @Override
    public int getSpeed() {
        return super.getSpeed();
    }

    @Override
    public int getReloadTime() {
        return super.getReloadTime();
    }
}
