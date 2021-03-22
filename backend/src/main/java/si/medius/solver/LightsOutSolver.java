package si.medius.solver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import si.medius.entity.Problem;
import si.medius.entity.Solution;
import si.medius.entity.SolutionStep;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class LightsOutSolver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public LightsOutSolver() { }

    @Nullable
    public Solution solve(Problem problem) {
        long startTime = System.currentTimeMillis();
        int n = problem.getSize();
        int[] description = parseDescription(problem);
        boolean[][] toggle = toggleMatrix(n);

        gaussianElimination(toggle, description, n);
        boolean[] result;
        try {
            result = backSubstitute(toggle, description, n);
        } catch (RuntimeException e) {
            return null;
        }

        Solution solution = new Solution();
        solution.setProblem(problem);
        ArrayList<SolutionStep> steps = new ArrayList<>(result.length);
        StringBuilder log = new StringBuilder();

        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                SolutionStep step = new SolutionStep();
                step.setSolution(solution);
                step.setIndex(i);
                steps.add(step);
                log.append(i);
                log.append(" ");
            }
        }

        solution.setSteps(steps);
        long endTime = System.currentTimeMillis();

        logger.info("Solved in " + (endTime - startTime) + " milliseconds");
        logger.info("Solution indices: " + log.toString());

        return solution;
    }

    private int[] parseDescription(Problem problem) {
        int dim = problem.getSize();
        dim *= dim;

        int[] description = new int[dim];

        for (int i = 0; i < dim; i++) {
            description[i] = Integer.parseInt(problem.getDescription().substring(i, i + 1));
        }

        return description;
    }

    /**
     * Returns a toggle matrix of size (N*N)x(N*N). Each column tells us which
     * lights would be affected by switching that square.
     */
    private boolean[][] toggleMatrix(int n) {
        boolean[][] matrix = new boolean[n * n][n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int col = rowMajorIndex(i, j, n);

                // The pressed light is always affected
                matrix[col][col] = true;

                // Mark neighbours as affected
                for (int di = -1; di <= 1 ; di++) {
                    for (int dj = -1; dj <= 1 ; dj++) {
                        if ((di == 0) == (dj == 0)) {
                            continue;
                        }

                        if ((i + di < n && j + dj < n) && (i + di >= 0 && j + dj >= 0)) {
                            matrix[col][rowMajorIndex(i + di, j + dj, n)] = true;
                        }
                    }
                }
            }
        }

        return matrix;
    }

    private int rowMajorIndex(int i, int j, int n) {
        return i * n + j;
    }

    /**
     *
     * @param matrix NxN toggle matrix
     * @param problem Problem description
     */
    private void gaussianElimination(boolean[][] matrix, int[] problem, int n) {
        int nextFreeRow = 0;

        for (int col = 0; col < matrix.length; col++) {
            int pivotRow = findPivot(matrix, nextFreeRow, col);
            if (pivotRow == -1) {
                continue;
            }

            swapRow(matrix, pivotRow, nextFreeRow);
            swapElement(problem, pivotRow, nextFreeRow);

            for (int row = pivotRow + 1; row < matrix.length; row++) {
                if (matrix[row][col]) {
                    for (int i = 0; i < matrix.length; i++) {
                        boolean rowWithPivot = matrix[nextFreeRow][i];
                        boolean rowToClear = matrix[row][i];

                        matrix[row][i] = rowWithPivot != rowToClear;
                    }

                    problem[row] ^= problem[nextFreeRow];
                }
            }

            nextFreeRow++;
        }
    }

    private int findPivot(boolean[][] matrix, int startRow, int pivotColumn) {
        for (int row = startRow; row < matrix.length; ++row) {
            if (matrix[row][pivotColumn]) {
                return row;
            }
        }

        return -1;
    }

    private void swapRow(boolean[][] matrix, int row1, int row2) {
        boolean[] tmp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = tmp;
    }

    private void swapElement(int[] a, int index1, int index2) {
        int tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    private boolean[] backSubstitute(boolean[][] matrix, int[] problem, int n) throws RuntimeException {
        boolean[] result = new boolean[n * n];
        Arrays.fill(result, false);

        for (int row = matrix.length; row-- != 0; ) {
            int pivot = -1;

            for (int col = 0; col < matrix[row].length; ++col) {
                if (matrix[row][col]) {
                    pivot = col;
                    break;
                }
            }

            if (pivot == -1) {
                if (problem[row] > 0) {
                    throw new RuntimeException("No solution");
                }
            } else {
                result[row] = problem[row] > 0;
                for (int col = pivot + 1; col < matrix.length; ++col) {
                    result[row] = (result[row] != (matrix[row][col] & result[col]));
                }
            }
        }

        return result;
    }

    /**
     * Code for debug purposes
     */
//    private void printMatrix(boolean[][] matrix) {
//        for (int i = 0; i < matrix.length; i++) {
//            StringBuilder log = new StringBuilder();
//
//            for (int j = 0; j < matrix.length; j++) {
//                log.append(matrix[i][j] ? "1" : "0");
//            }
//            logger.debug(log.toString());
//        }
//    }
//
//    private String mtxToStr(boolean[][] mtx) {
//        StringBuilder s = new StringBuilder();
//
//        for (int i = 0; i < mtx.length; i++) {
//            for (int j = 0; j < mtx.length; j++) {
//                s.append(mtx[i][j] ? "1" : "0");
//            }
//            s.append("\n");
//        }
//
//        return s.toString();
//    }
}
