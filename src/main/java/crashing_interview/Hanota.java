
package crashing_interview;

import java.util.List;

public class Hanota {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int size = A.size();
        move(size, A, B, C);
    }

    private void move(int n , List<Integer> source, List<Integer> mediator, List<Integer> target) {
        if (n == 1) {
            target.add(source.remove(source.size() - 1));
            return;
        }

        move(n - 1, source, target, mediator);
        target.add(source.remove(source.size() - 1));
        move(n - 1, mediator, source, target);
    }
}
