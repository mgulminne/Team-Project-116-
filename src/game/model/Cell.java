package game.model;

public abstract class Cell {
    private int row;
    private int col;
    private char symbol;

    public Cell(int row ,int col,char symbol){
        this.row=row;
        this.col=col;
        this.symbol=symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getSymbol() {
        return symbol;
    }
    public abstract boolean isConnectable();  //Elektrik ve su geçişi için
    public abstract boolean isZone();         //bölge kontrolü konut,ticari,endüstriyel
    public abstract boolean isEmpty();        //hücrenin boş olup olmadığı kontrolü
}

