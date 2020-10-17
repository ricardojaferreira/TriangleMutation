package triangle;

import org.junit.Test;
import static org.junit.Assert.*;

import static triangle.Triangle.Type;
import static triangle.Triangle.Type.*;

/**
 * Test class for the Triangle implementation.
 */
public class TriangleTest {

  /**
   * Wrapper to avoid code duplication in test methods.
   */
  /***
   * Comment the assert statement the coverage remains at 100% but the mutation test does not kill any mutant.
   */
  private void testTriangle(int a, int b, int c, Type expected) {
    Type actual = Triangle.classify(a, b, c);

    assertEquals(expected, actual);
  }

  @Test
  public void testDummy() {
    // Cover the default constructor to make Cobertura happy.
    Triangle a = new Triangle();
  }

  @Test
  public void testTable() {
    testTriangle(0, 0, 0, INVALID);
    testTriangle(1, 0, 0, INVALID);
    testTriangle(1, 1, 0, INVALID);
    testTriangle(1, 2, 3, INVALID);
    testTriangle(1, 1, 1, EQUILATERAL);
    testTriangle(2, 2, 1, ISOSCELES);
    testTriangle(2, 3, 4, SCALENE);
    testTriangle(1, 2, 4, INVALID);
    testTriangle(1, 4, 2, INVALID);
    testTriangle(4, 1, 2, INVALID);
    testTriangle(2, 2, 4, INVALID);
    testTriangle(2, 1, 2, ISOSCELES);
    testTriangle(2, 4, 2, INVALID);
    testTriangle(1, 2, 2, ISOSCELES);
    testTriangle(4, 2, 2, INVALID);
  }

  /***
   * Notes:
   * Total Mutants Covered: 150
   * Total Killed in the first run: 119
   * Live mutants in the first run: 2 3 4 7 8 9 11 12 13 18 22 59 63 70 73 76 80 81 84 87 101 105 108 111 122 125 128 136 139 142 145
   * Mutant 59: Changes line 33 to trian<=0 and this condition will never happen, so this mutant is equivalent
   * Mutant 87: Is equivalent since the condition introduced will always behave has the original
   * Mutant 101: Is equivalent since the code to meet the condition will never reach the line where the mutant was introduced, 43. Is validated on line 33.
   * Mutant 111: Is equivalent. If trian==1, a + b > c must also be true and that is the same has the original condition. On the other hand if trian != 1,
   * implies that trian must be equal to 2 or 3 and a + b > c must be FALSE and that never happens for these values of trian.
   * Mutant 128: Is equivalent. If trian==2 and a+c>b is the original condition. If trian != 2, then a+c>b must be false and that is not possible since in
   * this case b==c and a + c is always bigger than b
   * Mutant 136: Is equivalent since to get to the mutant line b == c and in that case trian == 3, so the mutant gets equal to the original condition
   * Mutant 145: Is equivalent. If trian==3 and b+c>a is the original conditional. If trian != 3 the code will not reach the mutation line.
   *
   * After TestMutants:
   * Total Mutants Covered: 150
   * Total Killed Mutants: 140
   * Live Mutants remaining: 11 59 76 87 101 111 128 136 139 145
   */
  @Test
  public void testMutants() {
    testTriangle(0, 1, 1, INVALID); //Kill mutants 2 and 3
    testTriangle(-1, 1, 1, INVALID); //Kill mutant 4
    testTriangle(1, 0, 1, INVALID); //Kill mutants 7 and 8
    testTriangle(1, -1, 1, INVALID); //Kill mutant 9

    //Mutant 11 is equivalent
    testTriangle(-1, 1, 1, INVALID);
    testTriangle(1, -1, 1, INVALID);
    testTriangle(-1, -1, 1, INVALID);

    testTriangle(1, 1, -1, INVALID); //Kill mutant 18
    testTriangle(2, 3, 5, INVALID); //Kill mutant 63
    testTriangle(2, 5, 3, INVALID); //Kill mutants 70 and 73

    //Mutant 76 is equivalent
    testTriangle(3, 10, 7, INVALID);

    testTriangle(6, 4, 5, SCALENE); //Kill Mutant 80
    testTriangle(5, 3, 2, INVALID); //Kill Mutant 81
    testTriangle(3, 3, 7, INVALID); //Kill Mutants 105 and 108
    testTriangle(3, 8, 3, INVALID); //Kill Mutant 122
    testTriangle(2, 6, 2, INVALID); //Kill Mutant 125
    testTriangle(5, 2, 2, INVALID); //Kill Mutant 142
  }

}
