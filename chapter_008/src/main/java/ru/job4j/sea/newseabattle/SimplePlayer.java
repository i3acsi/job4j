package ru.job4j.sea.newseabattle;

public abstract class SimplePlayer implements IPlayerStrategy {
    protected String name;
    protected Table myTable;
    protected int tableSize;
    protected IDisplayStrategy displayStrategy;


    public SimplePlayer(int size, String name, IDisplayStrategy display) {
        this.myTable = new Table(size);
        this.name = name;
        this.tableSize = size;
        this.displayStrategy = display;
    }

    @Override
    public int acceptDamage(int x, int y) {
        return myTable.shoot(x, y);
    }

    @Override
    public boolean isLose() {
        return myTable.isLose();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void congratulations() {

        this.displayStrategy.congratulations(this);
    }

    @Override
    public Table getTable() {
        return myTable;
    }

    @Override
    public void shootResultAction(int result) {

        String msg;
        switch (result) {
            case 2:
                msg = "Р А Н И Л!";
                break;
            case 3:
                msg = "У Б И Л ! ! !";
                break;
            case 5:
                msg = "П О В Т О Р Н О Е\t\t\tП О П А Д А Н И Е ! ! !";
                break;
            default:
                msg = "М И М О ! ! !";
                break;
        }
        displayStrategy.accept(msg);

    }

    @Override
    public void display(SimplePlayer other) {
        displayStrategy.display(this, other);
    }

    @Override
    public void display() {
        displayStrategy.show(this, false);
    }

    @Override
    public void accept(String msg) {
        displayStrategy.accept(msg);
    }

    @Override
    public void accept(int[] coordinate) {
        displayStrategy.accept(coordinate);
    }
}
