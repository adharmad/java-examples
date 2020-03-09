package com.example.javaexamples.lambda;

public class ComplexNumberLambdas {
    public static class ComplexNumber {
        double real;
        double complex;

        public ComplexNumber(double re, double cplx) {
            real = re;
            complex = cplx;
        }

        public String toString() {
            return "(" + real + (complex > 0? "+" : "-") + "i" + Math.abs(complex) + ")";
        }
    }

    interface ComplexOperation {
        ComplexNumber operation(ComplexNumber first, ComplexNumber second);
    }

    public static void main(String[] args) {
        ComplexOperation addComplex = (ComplexNumber cn1, ComplexNumber cn2)
            -> new ComplexNumber(cn1.real + cn2.real, cn1.complex + cn2.complex );

        ComplexOperation subtractComplex = (ComplexNumber cn1, ComplexNumber cn2)
                -> new ComplexNumber(cn1.real - cn2.real, cn1.complex - cn2.complex );

        ComplexOperation multiplyComplex = (ComplexNumber cn1, ComplexNumber cn2)
                -> new ComplexNumber(cn1.real * cn2.real - cn1.complex * cn2.complex,
                cn1.real * cn2.complex + cn1.complex * cn2.real);

        ComplexOperation divideComplex = (ComplexNumber cn1, ComplexNumber cn2)
                -> {
                double modulus = Math.sqrt(Math.pow(cn2.real, 2.0) + Math.pow(cn2.complex, 2.0));
                return new ComplexNumber((cn1.real * cn2.real + cn1.complex * cn2.complex) / modulus,
                    (cn1.real * cn2.complex - cn1.complex * cn2.real) / modulus);
            };

        ComplexNumber x = new ComplexNumber(1, 2);
        ComplexNumber y = new ComplexNumber(3, 4);

        System.out.println(x + " + " + y + " = " + addComplex.operation(x, y));
        System.out.println(x + " - " + y + " = " + subtractComplex.operation(x, y));
        System.out.println(x + " * " + y + " = " + multiplyComplex.operation(x, y));
        System.out.println(x + " / " + y + " = " + divideComplex.operation(x, y));
    }
}
