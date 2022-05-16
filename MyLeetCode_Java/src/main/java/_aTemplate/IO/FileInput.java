package _aTemplate.IO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 用于大数据的读入，不用手动输入那么麻烦。BufferedInputStream缓冲流来加速，
 * 文件输入流用绝对路径，避免不必要的麻烦，或把资源文件放在src目录下。
 */

public class FileInput {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(
                new BufferedInputStream(new FileInputStream("/tmp/input.txt")));
        List<Integer> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(sc.nextInt());
        }
        System.out.println(list);
    }

}
