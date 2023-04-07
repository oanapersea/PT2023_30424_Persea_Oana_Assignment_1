package Calculator;

import java.util.*;
import java.util.stream.IntStream;

public class Operations {

    static Polynomial subtractPolynomials(Polynomial poly1, Polynomial poly2) {
        Polynomial result = new Polynomial();

        poly1.getMap().keySet().forEach(key -> {
            List<Double> coeff1 = poly1.getMap().get(key);
            List<Double> coeff2 = poly2.getMap().get(key);
            List<Double> coeffRes = new LinkedList<>();

            if (coeff2 != null) {
                Iterator<Double> it1 = coeff1.iterator();
                Iterator<Double> it2 = coeff2.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    double c1 = it1.next();
                    double c2 = it2.next();
                    coeffRes.add(c1 - c2);
                }

                while (it2.hasNext()) {
                    coeffRes.add(-it2.next());
                }
            } else {
                coeffRes.addAll(coeff1);
            }
            result.getMap().put(key, coeffRes);
        });

        poly2.getMap().keySet().forEach(key -> {
            if (!poly1.getMap().containsKey(key)) {
                List<Double> coeff2 = poly2.getMap().get(key);
                List<Double> coeffRes = new LinkedList<>();
                for (double coeff : coeff2) {
                    coeffRes.add(-coeff);
                }
                result.getMap().put(key, coeffRes);
            }
        });

        Map<Integer, List<Double>> mapResult = new TreeMap<>(result.getMap()).descendingMap();
        result.setMap(mapResult);
        return result;

    }

    static Polynomial addPolynomials(Polynomial poly1, Polynomial poly2) {
        Polynomial result = new Polynomial();

        poly1.getMap().keySet().forEach(key-> {
            List<Double> coeff1 = poly1.getMap().get(key);
            List<Double> coeff2 = poly2.getMap().get(key);
            List<Double> coeffRes = new LinkedList<>();

            if (coeff2 != null) {
                Iterator<Double> it1 = coeff1.iterator();
                Iterator<Double> it2 = coeff2.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    coeffRes.add(it1.next() + it2.next());
                }
                while (it2.hasNext()) {
                    coeffRes.add(it2.next());
                }
            } else {
                coeffRes.addAll(coeff1);
            }
            result.getMap().put(key, coeffRes);

        });

        poly2.getMap().keySet().forEach(key -> {
            if (!poly1.getMap().containsKey(key)) {
                result.getMap().put(key, poly2.getMap().get(key));
            }
        });

        Map<Integer, List<Double>> mapResult = new TreeMap<>(result.getMap()).descendingMap();
        result.setMap(mapResult);
        return result;

    }

    public static Polynomial multiplyPolynomials(Polynomial poly1, Polynomial poly2) {
       Polynomial result=new Polynomial();

        poly1.getMap().keySet().forEach(power1 -> {
            List<Double> coeffs1 = poly1.getMap().get(power1);
            poly2.getMap().keySet().forEach(power2 -> {
                List<Double> coeffs2 = poly2.getMap().get(power2);
                int power = power1 + power2;
                List<Double> coeffs = result.getMap().getOrDefault(power, new ArrayList<>());
                IntStream.range(0, coeffs1.size()).forEach(i -> {
                    IntStream.range(0,coeffs2.size()).forEach(j -> {
                        double product = coeffs1.get(i) * coeffs2.get(j);
                        int summandIndex = i + j;
                        if (coeffs.size() > summandIndex) {
                            coeffs.set(summandIndex, coeffs.get(summandIndex) + product);
                        } else {
                            coeffs.add(summandIndex, product);
                        }

                    });
                }); result.getMap().put(power, coeffs);

            });

        });
        Map<Integer, List<Double>> mapResult = new TreeMap<>(result.getMap()).descendingMap();
        result.setMap(mapResult);
        return result;
    }

    public static Polynomial derivativePolynomials(Polynomial poly) {
        Polynomial result = new Polynomial();

        poly.getMap().entrySet().forEach(term -> {
            int exponent = term.getKey();
            List<Double> coefficients = term.getValue();

            if (exponent > 0) {
                List<Double> derivativeCoefficients = new ArrayList<>();
                for (int i = 0; i < coefficients.size(); i++) {
                    derivativeCoefficients.add(coefficients.get(i) * exponent);
                    exponent--;
                }
                result.getMap().put(exponent, derivativeCoefficients);
            }
        });

        Map<Integer, List<Double>> mapResult = new TreeMap<>(result.getMap()).descendingMap();
        result.setMap(mapResult);
        return result;
    }


    public static Polynomial antiderivativePolynomials(Polynomial poly) {
        Polynomial result=new Polynomial();

        poly.getMap().entrySet().forEach(term-> {
            int exponent = term.getKey();

            List<Double> coefficients = term.getValue();
            List<Double> antiderivativeCoefficients = new ArrayList<>();
            IntStream.range(0, coefficients.size()).forEach(i -> {
                antiderivativeCoefficients.add(coefficients.get(i) / (exponent + 1.0));
            });
            result.getMap().put(exponent + 1, antiderivativeCoefficients);
        });


        Map<Integer, List<Double>> mapResult = new TreeMap<>(result.getMap()).descendingMap();
        result.setMap(mapResult);
        return result;
    }

}