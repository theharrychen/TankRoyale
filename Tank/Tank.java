import java.util.ArrayList;

public class Tank {

    private String tankName;
    private String projectileType;
    private ArrayList<Projectile> projectiles;
    private float health;
    private float experience;
    private int currentLevel;

    private int x;
    private int y;
    private int direction;

    public Tank(String projectileType)
    {
        this.tankName = "Tank1";
        this.projectileType = projectileType;
        this.projectiles = new ArrayList<Projectile>();
        this.health = 100;
        this.experience = 0;
        this.currentLevel = 1;
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        addProjectiles(projectileType);
    }

    public void move(int x, int y)
    {
        this.x = x;
        this.y = y;
        System.out.println("X: " + this.x + "," + " Y: " + this.y);
    }

    public void rotate(int direction)
    {
        if (direction == 90)
        {
            this.direction = direction;
            System.out.println("Facing UP");
        }
        if (direction == 180)
        {
            this.direction = direction;
            System.out.println("Facing LEFT");
        }
        else if (direction == 270)
        {
            this.direction = direction;
            System.out.println("Facing DOWN");
        }
        else if (direction == 0)
        {
            this.direction = direction;
            System.out.println("Facing RIGHT");
        }
        else
        {
            System.out.println("Not a proper direction given");
        }
    }

    public void addHealth(float amount)
    {
        float maxHealth = this.health * (float)this.currentLevel;
        if (this.health <= maxHealth)
        {
            this.health += amount;
        }
        else
        {
            System.out.println("Tank already has full health");
        }
    }

    public void depleteHealth(float amount)
    {
        if (this.health - amount <= 0)
        {
            System.out.println("Game Over");
        }
        else
        {
            this.health -= amount;
        }
    }

    private void levelUp()
    {
        System.out.println("You leveled up!");
        this.currentLevel += 1;
        this.health *= this.currentLevel;

    }

    public void shoot(int x, int y)
    {
        if (this.projectiles.isEmpty())
        {
            System.out.println("No ammo left. You must reload");
            System.out.println("No shot was taken");
        }
        else
        {
            this.projectiles.get(this.projectiles.size() - 1).fire(x, y);
            this.projectiles.remove(this.projectiles.size() - 1);
            this.experience += 15;
            if (this.experience >= 100)
            {
                this.experience = 0;
                levelUp();
            }
        }

    }

    private void addProjectiles(Projectile projectile)
    {
        if (this.projectiles.isEmpty())
        {
            while (this.projectiles.size() < 5)
            {
                this.projectiles.add(projectile);
            }
        }
    }

    private void addProjectiles(String projectileType)
    {
        String validType = projectileType.toLowerCase();
        this.projectileType = validType;
        if (validType.equals("peacekeeper"))
        {
            addProjectiles(new Peackeeper());
        }
        else if (validType.equals("wingman"))
        {
            addProjectiles(new Wingman());
        }
        else if (validType.equals("g7"))
        {
            addProjectiles(new G7());
        }
        else
        {
            System.out.println("No proper projectiles type was given");
            this.projectileType = "peacekeeper";
            System.out.println("Default projectiles Type: " + this.projectileType);
            addProjectiles(new Peackeeper());
        }
    }

    public void reload()
    {
        String currentProjectileType = this.projectileType.toLowerCase();
        if (this.projectiles.size() < 5)
        {
            if (currentProjectileType.equals("peacekeeper"))
            {
                this.projectiles.add(new Peackeeper());
            }
            else if (currentProjectileType.equals("wingman"))
            {
                this.projectiles.add(new Wingman());
            }
            else if (currentProjectileType.equals("g7"))
            {
                this.projectiles.add(new G7());
            }
            else
            {
                System.out.println("No proper projectiles type was given");
            }
        }
        else
        {
            System.out.println("Max Ammo");
        }
    }

    public float getProjectileDamage()
    {
        if (!this.projectiles.isEmpty())
        {
            return this.projectiles.get(0).getDamage();
        }
        else
        {
            return 0.0f;        }
    }

    public String getProjectileType()
    {
        if (!this.projectiles.isEmpty())
        {
            return this.projectiles.get(0).getName();
        }
        else
        {
            return "";
        }
    }

    public int getProjectileReloadTime()
    {
        if (!this.projectiles.isEmpty())
        {
            return this.projectiles.get(0).getReloadTime();
        }
        else
        {
            return -1;
        }
    }

    public int getAmmo()
    {
        return this.projectiles.size();
    }

    public float getHealth()
    {
        return this.health;
    }

    public String getTankName()
    {
        return this.tankName;
    }

    public int getDirection()
    {
        return this.direction;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setTankName(String name)
    {
        this.tankName = name;
    }

    public String toString()
    {
        return "Tank Name: " + this.tankName + "\n"
                + "Health: " + this.health + "\n"
                + "Projectile Type: " + this.projectileType.toUpperCase() + "\n"
                + "Experience: " + this.experience + "\n"
                + "Level: " + this.currentLevel + "\n"
                + "X Pos: " + this.x + "\n"
                + "Y Pos: " + this.y + "\n"
                + "Current Direction: " + this.direction + " Degrees \n"
                + "Ammo: " + this.projectiles.size() + "\n";
    }

}
