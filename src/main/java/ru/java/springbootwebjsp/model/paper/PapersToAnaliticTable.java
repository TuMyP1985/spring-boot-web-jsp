package ru.java.springbootwebjsp.model.paper;

//import org.jetbrains.annotations.NotNull;

public class PapersToAnaliticTable implements Comparable<PapersToAnaliticTable> {

    private RichPaper richPaper;
    private double minusPercentForSort;
    private double plusPercentForSort;

    public PapersToAnaliticTable() {
    }

    public PapersToAnaliticTable(RichPaper richPaper, double minusPercentForSort, double plusPercentForSort) {
        this.richPaper = richPaper;
        this.minusPercentForSort = minusPercentForSort;
        this.plusPercentForSort = plusPercentForSort;
    }

    public PapersToAnaliticTable(RichPaper richPaper) {
        this.richPaper = richPaper;
    }

    public RichPaper getRichPaper() {
        return richPaper;
    }

    public void setRichPaper(RichPaper richPaper) {
        this.richPaper = richPaper;
    }

    public double getMinusPercentForSort() {
        return minusPercentForSort;
    }

    public void setMinusPercentForSort(double minusPercentForSort) {
        this.minusPercentForSort = minusPercentForSort;
    }

    public double getPlusPercentForSort() {
        return plusPercentForSort;
    }

    public void setPlusPercentForSort(double plusPercentForSort) {
        this.plusPercentForSort = plusPercentForSort;
    }

    @Override
    public int compareTo(PapersToAnaliticTable o) {
//        int first = (int) (o.getMinusPercentForSort() * 100 - this.getMinusPercentForSort() * 100);
//        return first != 0 ? first : (int) (o.getPlusPercentForSort() * 100 - this.getPlusPercentForSort() * 100);

        return (int) ((o.getMinusPercentForSort() + o.getPlusPercentForSort()) * 100 -
                (this.getMinusPercentForSort() + this.getPlusPercentForSort()) * 100);


    }
}
