package db;

/**
 * Created by jhinukbarman on 3/5/17.
 */
public class Multiplication extends ArithmeticOperators {
    private Column a;
    private Column b;
    private String name;
    private String s;
    private String unarycheck;
    public Multiplication(Column a, Column b, String name) {
        this.a = a;
        this.b = b;
        this.name = name;
    }

    public Multiplication(Column a, String s, String name, String unarycheck) {
        this.a = a;
        this.s = s;
        this.name = name;
        this.unarycheck = unarycheck;
    }

    @Override
    public Column combineCols() {

        if (!isSameColType(a, b)) {
            return new Column("NONAME", "string");
        }

        if (a.getMyType().equals("int") && b.getMyType().equals("int")) {
            Column newCol = new Column(name, "int");
            String newVal;
            for (int i = 0; i < a.getValues().size(); i++) {
                if (a.getValues().get(i).contains("'") || b.getValues().get(i).contains("'")) {
                    return new Column("NONAME", "string");
                }
               // if (a.getValues().get(i).equals("NaN") || b.getValues().equals("NaN"))
                if (a.getValues().get(i).equals("NaN") || b.getValues().get(i).equals("NaN")) {
                    newCol.addVal("NaN");
                } else {
                    int a1 = Integer.parseInt(a.getValues().get(i));
                    int b1 = Integer.parseInt(b.getValues().get(i));
                    newVal = String.valueOf(a1 * b1);
                    newCol.addVal(newVal);
                }
            }
            return newCol;
            /*}  else if (a.getMyType().equals("float") && b.getMyType().equals("float")) {
                Column newCol = new Column("c", "float");
                for (int i = 0; i < a.getValues().size(); i++) {
                    //convert
                    newCol.addVal(a.getValues().get(i) + b.getValues().get(i));
                }
                return newCol;
                */
        } else {
            //if ((a.getMyType().equals("int") && b.getMyType().equals("float")) ||
            // (a.getMyType().equals("float") && b.getMyType().equals("int"))) {
            String newVal;
            Column newCol = new Column(name, "float");
            for (int i = 0; i < a.getValues().size(); i++) {
                if (a.getValues().get(i).equals("NaN") || b.getValues().get(i).equals("NaN")) {
                    newCol.addVal("NaN");
                } else {
                    String s1 = a.getValues().get(i) + "f";
                    float f = Float.parseFloat(s1);
                    String s2 = b.getValues().get(i) + "f";
                    float f1 = Float.parseFloat(s2);
                    newVal = String.valueOf(f * f1);
                    newCol.addVal(newVal);
                }
            }
            return newCol;
        }

        // for ()
        //return new Column("NONAME", "string"); //replace this line
    }

    @Override
    public Column combineUnaryCols() {

        String correctType = a.getMyType();
        if (correctType.equals("string")) {
            return new Column("NONAME", "string"); //replace this line
        } else {
            if (correctType.equals("int") && (!(s.contains(".")))) {
                Column newCol = new Column(name, "int");
                for (int i = 0; i < a.getValues().size(); i++) {
                    int a1 = Integer.parseInt(a.getValues().get(i));
                    int b1 = Integer.parseInt(s);
                    if (b1 == 0) {
                        return new Column("NONAME", "string"); //replace this line
                    }
                    String newVal = String.valueOf(a1 / b1);
                    newCol.addVal(newVal);
                }
                return newCol;
            /*}  else if (a.getMyType().equals("float") && b.getMyType().equals("float")) {
                Column newCol = new Column("c", "float");
                for (int i = 0; i < a.getValues().size(); i++) {
                    //convert
                    newCol.addVal(a.getValues().get(i) + b.getValues().get(i));
                }
                return newCol;
                */
            } else {

                //if ((a.getMyType().equals("int") && b.getMyType().equals("float")) ||
                // (a.getMyType().equals("float") && b.getMyType().equals("int"))) {
                Column newCol = new Column(name, "float");
                for (int i = 0; i < a.getValues().size(); i++) {
                    String s1 = a.getValues().get(i) + "f";
                    float f = Float.parseFloat(s1);
                    String s2 = s + "f";
                    float f1 = Float.parseFloat(s2);
                    if (f1 == 0) {
                        return new Column("NONAME", "string"); //replace this line
                    }
                    String newVal = String.valueOf(f * f1);
                    newCol.addVal(newVal);
                }
                return newCol;
            }




        }



        // for ()
        //return new Column("NONAME", "string"); //replace this line
    }
}
