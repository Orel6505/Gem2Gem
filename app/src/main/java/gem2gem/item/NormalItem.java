package gem2gem.item;

public class NormalItem extends Item
{
    public NormalItem(Character name)
    {
        this.name = name;
    }

    @Override
    public void moveFunction()
    {
        // Implement the move function for normal items
        System.out.println("Normal item " + name + " moved.");
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
    
}
