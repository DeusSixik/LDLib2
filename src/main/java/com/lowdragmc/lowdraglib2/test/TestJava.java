package com.lowdragmc.lowdraglib2.test;

import net.sixik.ga_profiler.HtmlReporter;
import net.sixik.ga_profiler.ProfileData;
import net.sixik.ga_profiler.Profiler;

import java.util.*;

public class TestJava {
    public class A<T> {
        public List<T> list;
        public T t;
    }

    public class B extends A<Boolean> {

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(B.class.getFields()));

        List<String> specs = List.of(
                "Ryzen Threadripper 1950X",
                "RTX 5070",
                "RAM 64 GB (10 GB assigned)",
                "NVMe SSD"
        );

        Map<String, Collection<ProfileData.Snapshot>> comparison = new LinkedHashMap<>();

        List<ProfileData.Snapshot> no_path = new ArrayList<>();
        no_path.addAll(Profiler.load("E:\\JavaProjects\\LDLib2\\runs\\client\\run_v2.dump"));
        no_path.addAll( Profiler.load("E:\\JavaProjects\\LDLib2\\runs\\client\\graph_no_path.dump"));

        List<ProfileData.Snapshot> path = new ArrayList<>();
        path.addAll(Profiler.load("E:\\JavaProjects\\LDLib2\\runs\\client\\run_v1.dump"));
        path.addAll( Profiler.load("E:\\JavaProjects\\LDLib2\\runs\\client\\graph_path.dump"));

        comparison.put("LDLib", no_path);
        comparison.put("LDLib Path", path);
//        comparison.put("LDLib_ Path", Profiler.load("E:\\JavaProjects\\LDLib2\\runs\\client\\graph_path.dump"));

        HtmlReporter.generateComparison("comparison_report.html", comparison, specs);
    }
}
