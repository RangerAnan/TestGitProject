package com.test.mi.testproject.library;


import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by fcl on 18.7.12
 * desc:
 */
public class TestPlugin implements Plugin<Project> {


    @Override
    public void apply(Project project) {
        System.out.print("TestPlugin");
    }
}
