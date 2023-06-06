import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> homes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        homes = new HashMap<>();

        int left, right;
        List<Integer> tmp;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            left = Integer.parseInt(st.nextToken());
            right = Integer.parseInt(st.nextToken());
            if (homes.get(left) == null) {
                tmp = new ArrayList<>();
                tmp.add(right);
                homes.put(left, tmp);
            } else {
                homes.get(left).add(right);

            }
            if (homes.get(right) == null) {
                tmp = new ArrayList<>();
                tmp.add(left);
                homes.put(right, tmp);
            } else {
                homes.get(right).add(left);
            }
        }
//        System.out.println(homes);

        Map<Integer, Long> resultMap = goDeeper(1, 0, 0);

        System.out.println(resultMap.get(-1));
    }

    private static Map<Integer, Long> goDeeper(int currentNode, int currentLevel, int parentNode) {
        //현재 노드가 가지고있는 자식노드 리스트
        List<Integer> childrenNodeList = homes.get(currentNode);

        // 해당 노드 아래에 있는 자식들의 개수 저장, <~레벨, ~개>
        Map<Integer, Long> sumMap = new HashMap<>();
        Map<Integer, Long> leftChildrenMap = null;
        Map<Integer, Long> rightChildrenMap = null;

        int leftDiff, rightDiff, childNo;
        long sum = 0;
        boolean isLeft = false;

        // 위에 연결된 노드를 제외하고 자신 밑에 자식이 있는 경우
        if (childrenNodeList.size() != 1 || currentNode == 1) {
            // 해당 자식목록 순회
            for (int i = 0; i < childrenNodeList.size(); i++) {
                childNo = childrenNodeList.get(i);
                // 가지고 있던 부모 노드가 아닌 경우만
                if (childNo != parentNode) {
                    if (!isLeft) { // 누가 왼쪽 오른쪽인지는 기준에 따라 다르니 그냥 첫번째 자식 노드는 왼쪽으로 지정
                        isLeft = true;
                        leftChildrenMap = goDeeper(childNo, currentLevel + 1, currentNode);
                    } else {
                        rightChildrenMap = goDeeper(childNo, currentLevel + 1, currentNode);
                    }
                }
            }
// 왼, 우 자식 노드 둘 다 존재할 때
            if (leftChildrenMap != null && rightChildrenMap != null) {
                for (Map.Entry<Integer, Long> leftChildNode : leftChildrenMap.entrySet()) {
                    if (leftChildNode.getKey() == -1)
                        continue;
                    leftDiff = leftChildNode.getKey() - currentLevel;
                    for (Map.Entry<Integer, Long> rightChildNode :
                            rightChildrenMap.entrySet()) {
                        if (rightChildNode.getKey() == -1)
                            continue;
                        rightDiff = rightChildNode.getKey() - currentLevel;
                        sum += ((leftDiff + rightDiff + currentLevel) * (leftChildNode.getValue() * rightChildNode.getValue()));
                    }
                }

                if (parentNode != 0) {
                    // 하부 노드들이 현재 노드의 부모 노드와 교감할 때 해당 높이만큼 추가하게 됨. 왼,좌 자식 모두 처리
                    for (Map.Entry<Integer, Long> leftChildNode : leftChildrenMap.entrySet()) {
                        if (leftChildNode.getKey() == -1)
                            continue;
                        sum += leftChildNode.getKey() * leftChildNode.getValue();
                    }
                    for (Map.Entry<Integer, Long> rightChildNode : rightChildrenMap.entrySet()) {
                        if (rightChildNode.getKey() == -1)
                            continue;
                        sum += rightChildNode.getKey() * rightChildNode.getValue();
                    }
                }

                // 반환할 Map에 자식 노드의 데이터 삽입
                sumMap.putAll(leftChildrenMap);
                // Map의 putAll은 deepCopy처럼 붙여넣는 것과 동일하기 때문에 오른쪽 자식 데이터는 하나하나씩 넣어줌
                for (Map.Entry<Integer, Long> tmp : rightChildrenMap.entrySet()) {
                    sumMap.put(tmp.getKey(), sumMap.getOrDefault(tmp.getKey(), 0L) + tmp.getValue());
                }

                sumMap.put(-1, sum + leftChildrenMap.get(-1) + rightChildrenMap.get(-1));
            }
            // 자식이 존재하는 경우에 왼쪽 자식은 존재하지만 오른쪽 자식은 존재하지 않는 경우 존재
            else {
                sumMap.putAll(leftChildrenMap);
                if (parentNode != 0) {
                    for (Map.Entry<Integer, Long> leftChildNode : leftChildrenMap.entrySet()) {
                        if (leftChildNode.getKey() == -1)
                            continue;
                        sum += leftChildNode.getKey() * leftChildNode.getValue();
                    }
                }
                sumMap.put(-1, sum + leftChildrenMap.get(-1)); // 하부 작업물 저장
            }
        }

        // 리프노드인 경우와 자식 노드인 경우 모두 처리 가능한 공통 코드
        sumMap.put(-1, (long) currentLevel + sumMap.getOrDefault(-1, 0L));
        sumMap.put(currentLevel, 1L);
//        System.out.println("current Node : " + currentNode + ", sumMap: " + sumMap);
        return sumMap;
    }

}