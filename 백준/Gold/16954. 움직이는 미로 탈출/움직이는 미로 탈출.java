import java.io.*;
import java.util.*;

public class Main {
   static class Reader {
      final private int BUFFER_SIZE = 1 << 10;
      private DataInputStream din;
      private byte[] buffer;
      private int bufferPointer, bytesRead;

      public Reader() {
         din = new DataInputStream(System.in);
         buffer = new byte[BUFFER_SIZE];
         bufferPointer = bytesRead = 0;
      }

      public Reader(String file_name) throws IOException {
         din = new DataInputStream(new FileInputStream(file_name));
         buffer = new byte[BUFFER_SIZE];
         bufferPointer = bytesRead = 0;
      }

      public String readLine() throws IOException {
         byte[] buf = new byte[BUFFER_SIZE]; // line length
         int cnt = 0, c;
         while ((c = read()) != -1) {
            if (c == '\n') {
               if (cnt != 0) {
                  break;
               } else {
                  continue;
               }
            }
            buf[cnt++] = (byte) c;
         }
         return new String(buf, 0, cnt);
      }

      public int nextInt() throws IOException {
         int ret = 0;
         byte c = read();
         while (c <= ' ') {
            c = read();
         }
         boolean neg = (c == '-');
         if (neg)
            c = read();
         do {
            ret = ret * 10 + c - '0';
         } while ((c = read()) >= '0' && c <= '9');

         if (neg)
            return -ret;
         return ret;
      }

      public long nextLong() throws IOException {
         long ret = 0;
         byte c = read();
         while (c <= ' ')
            c = read();
         boolean neg = (c == '-');
         if (neg)
            c = read();
         do {
            ret = ret * 10 + c - '0';
         } while ((c = read()) >= '0' && c <= '9');
         if (neg)
            return -ret;
         return ret;
      }

      public double nextDouble() throws IOException {
         double ret = 0, div = 1;
         byte c = read();
         while (c <= ' ')
            c = read();
         boolean neg = (c == '-');
         if (neg)
            c = read();

         do {
            ret = ret * 10 + c - '0';
         } while ((c = read()) >= '0' && c <= '9');

         if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
               ret += (c - '0') / (div *= 10);
            }
         }

         if (neg)
            return -ret;
         return ret;
      }

      private void fillBuffer() throws IOException {
         bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
         if (bytesRead == -1)
            buffer[0] = -1;
      }

      private byte read() throws IOException {
         if (bufferPointer == bytesRead)
            fillBuffer();
         return buffer[bufferPointer++];
      }

      public void close() throws IOException {
         if (din == null)
            return;
         din.close();
      }
   }

   static class CustomComparator implements Comparator<Integer> {
      @Override
      public int compare(Integer o1, Integer o2) {
         // TODO Auto-generated method stub
         return o2 - o1;
      }
   }

   public static void main(String[] args) throws IOException {
      Reader rd = new Reader();
      char[][][] bd = new char[9][8][8];
      String one;
      Queue<int[]> q = new LinkedList<>();
      // 좌상, 상, 우상, 우, 우하, 하, 좌하, 좌
      int[][] dydx = new int[][] { //
            { -1, -1 }, { -1, 0 }, { -1, 1 }, //
            { 0, 1 }, { 1, 1 }, //
            { 1, 0 }, { 1, -1 }, { 0, -1 }, //
            { 0, 0 }
      };

      // 첫 입력은 0번째에 저장
      for (int i = 0; i < 8; i++) {
         bd[0][i] = rd.readLine().toCharArray();
//         for (int k = 0; k < 8; k++) {
//            System.out.print(bd[0][i][k]);
//         }
      }
      // 1~8회 이후 보드를 저장
      for (int i = 1; i < 9; i++) {
         // 한 줄 씩 내린 값을 저장
         for (int j = 7; j >= 1; j--) {
            for (int k = 0; k < 8; k++) {
               bd[i][j][k] = bd[i - 1][j - 1][k];
            }
         }
         // 최상단줄을 .으로 초기화
         for (int j = 0; j < 8; j++) {
            bd[i][0][j] = '.';
         }
//         for (int j = 0; j < 8; j++) {
//            for (int j2 = 0; j2 < 8; j2++) {
//               System.out.print(bd[i][j][j2]);
//            }
//            System.out.println();
//         }
//         System.out.println();
      }
      // 처음 캐릭터의 시작 자리 추가
      q.add(new int[] { 7, 0, 0 });
      int[] crnt;
      int nextY, nextX;

      // 8회 이동 보드는 무조건 깔끔한 보드
      while (!q.isEmpty()) {
         crnt = q.poll();

         
         // 우상 도착 시
         if (crnt[0] == 0 && crnt[1] == 7) {
            System.out.println(1);
            return;
         }
         
         
         
         // 가만히 있는 아이들을
         for (int i = 0; i < 9; i++) {
            nextY = crnt[0] + dydx[i][0];
            nextX = crnt[1] + dydx[i][1];

            // 범위 벗어날 시 pass
            if (nextY < 0 || nextY == 8 || nextX < 0 || nextX == 8)
               continue;
            // 다음 자리가 #일 경우 pass
            else if (bd[crnt[2]][nextY][nextX] == '#')
               continue;
            // 이동 후 위에 있는 자리가 #인 경우 pass
            // 더 이상 내려갈 것이 없는 경우
            if (crnt[2] == 8) {
               if (bd[8][nextY][nextX] == '.') {
                  q.add(new int[] { nextY, nextX, 8 });
               }
            }
            // 아직 더 내려올 것들이 남은 경우 해당 보드 기준으로
            else {
               if (bd[crnt[2] + 1][nextY][nextX] == '.') {
                  q.add(new int[] { nextY, nextX, crnt[2] + 1 });
               }
            }
            
         }
      }
      System.out.println(0);
   }
}