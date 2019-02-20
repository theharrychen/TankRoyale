public class Peackeeper extends  Projectile{

    public Peackeeper()
    {
        super("Peacekeeper", 40, 100, 7);
    }

    public void explodeNearGround()
    {
        float currentDamage = this.getDamage();
        currentDamage *= 5;
        this.setDamage(currentDamage);
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
