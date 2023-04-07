package Calculator;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    Map<Integer, List<Double>> polynomials = new HashMap<>();

    public void setMap(Map<Integer, List<Double>> polynomials) {
        this.polynomials = polynomials;
    }

    public Map<Integer, List<Double>> getMap() {
        return polynomials;
    }

    public static Polynomial addInMap(Polynomial result, double coeff, int exp) {

        if (result.getMap().get(exp) != null) {
            double val = result.getMap().get(exp).get(0);
            result.getMap().get(exp).set(0, val + coeff);
        } else {
            List<Double> term = new ArrayList<>();
            term.add(coeff);
            result.getMap().put(exp, term);
        }

        Map<Integer, List<Double>> sortedMap = new TreeMap<>(result.getMap()).descendingMap();
        result.getMap().putAll(sortedMap);

        return result;

    }

    public static Polynomial readInput(String str) {

        Pattern p = Pattern.compile("(-?\\b\\d+)[xX]\\^(-?\\d+\\b)");
        Pattern p3 = Pattern.compile("([-+]?\\d*?)?x(?![\\d\\w]*?\\^)");

        Polynomial result = new Polynomial();

        str = str.replaceAll("(?<![0-9])x", "1x");
        str = str.replaceAll("(?<=[+-]|^)\\d+(?=[+-]|$)", "$0x^0");

        Matcher m3 = p3.matcher(str);

        while (m3.find()) {
            String simpleCoef = m3.group();
            char charToRemove = 'x';
            simpleCoef = simpleCoef.replace(Character.toString(charToRemove), "");
            int simpleCoeff = Integer.valueOf(simpleCoef);
            String newString = new String();
            if (simpleCoeff > 0) {
                newString = "+" + simpleCoeff + "x^1";
            } else {
                newString = simpleCoeff + "x^1";
            }
            str = str.replaceAll("([-+]?\\d*?)?x(?![\\d\\w]*?\\^)", newString);
        }

        Matcher m = p.matcher(str);
        while (m.find()) {
            String coef = m.group(1);
            int coeff = Integer.valueOf(coef);
            String po = m.group(2);
            int power = Integer.valueOf(po);
            result = addInMap(result, coeff, power);

        }
        return result;
    }

    public static String polyToString(Polynomial result) {
        StringBuilder sb = new StringBuilder();
        AtomicReference<Integer> iteration = new AtomicReference<>(1);
        result.polynomials.entrySet().forEach(entry -> {
            Integer key = entry.getKey();
            List<Double> list = entry.getValue();
            list.forEach(num -> {
                if (num != 0) {
                    if (key > 1) {
                        if (num > 0 && iteration.get() != 1) {
                            sb.append('+');
                        }
                        if (num != 1 && num != -1) {
                            sb.append(num + "x^" + key);
                        } else {
                            if (num == -1) {
                                sb.append('-');
                            }
                            sb.append("x^" + key);
                        }
                    } else {
                        if (key == 0) {
                            if (iteration.get() != 1) {
                                if (num > 0) {
                                    sb.append("+");
                                }
                                sb.append(num);
                            } else {
                                sb.append(num);
                            }

                        } else {
                            if (key == 1) {
                                if (iteration.get() != 1) {
                                    if (num != 1 && num != -1) {
                                        if (num > 0) {
                                            sb.append('+');
                                        }
                                        sb.append(num + "x");
                                    } else {
                                        if (num == 1) {
                                            sb.append("+" + "x");
                                        } else {
                                            if (num == -1) {
                                                sb.append("-" + "x");
                                            }
                                        }
                                    }
                                } else {
                                    if (num == 1) {
                                        sb.append("x");
                                    } else {
                                        if (num == -1) {
                                            sb.append("-" + "x");
                                        } else {
                                            sb.append(num + "x");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    iteration.getAndSet(iteration.get() - 1);
                }

                iteration.getAndSet(iteration.get() + 1);
            });
        });
//        for (Map.Entry<Integer, List<Double>> entry : result.polynomials.entrySet()) {
//            Integer key = entry.getKey();
//            List<Double> list = entry.getValue();
//            for (double num : list) {
//                if (num != 0) {
//                    if (key > 1) {
//                        if (num > 0 && iteration != 1) {
//                            sb.append('+');
//                        }
//                        if (num != 1 && num != -1) {
//                            sb.append(num + "x^" + key);
//                        } else {
//                            if (num == -1) {
//                                sb.append('-');
//                            }
//                            sb.append("x^" + key);
//                        }
//                    } else {
//                        if (key == 0) {
//                            if (iteration != 1) {
//                                if (num > 0) {
//                                    sb.append("+");
//                                }
//                                sb.append(num);
//                            } else {
//                                sb.append(num);
//                            }
//
//                        } else {
//                            if (key == 1) {
//                                if (iteration != 1) {
//                                    if (num != 1 && num != -1) {
//                                        if (num > 0) {
//                                            sb.append('+');
//                                        }
//                                        sb.append(num + "x");
//                                    } else {
//                                        if (num == 1) {
//                                            sb.append("+" + "x");
//                                        } else {
//                                            if (num == -1) {
//                                                sb.append("-" + "x");
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    if (num == 1) {
//                                        sb.append("x");
//                                    } else {
//                                        if (num == -1) {
//                                            sb.append("-" + "x");
//                                        } else {
//                                            sb.append(num + "x");
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    iteration--;
//                }
//
//                iteration++;
//            }
//        }

        if (sb.isEmpty()) {
            sb.append('0');
        }
        String resultString = sb.toString();
        return resultString;

    }
}




