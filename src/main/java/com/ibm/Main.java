package com.ibm;

import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String root_dir = "C:\\Users\\AwalpreetKaur\\Desktop\\My Data";
        JSONObject root_obj = new JSONObject();
        File rootFolder = new File(root_dir);

        //Call recursive function
        try {
            root_obj = renderFolder(rootFolder);
        } catch (NullPointerException e) {
            System.out.println("Invalid path!");
        }

        //Output
        System.out.println(root_obj.toJSONString());
    }

    /**
     * Recursive method to render contents of a folder
     *
     * @param filee
     * @return
     */
    private static JSONObject renderFolder(File filee) {
        JSONObject renderedFolder = new JSONObject();
        renderedFolder.put("name", filee.getName());

        List<JSONObject> children = new ArrayList<>();

        File[] filesList = filee.listFiles();
        for (File file : filesList) {
            JSONObject obj = new JSONObject();
            if (file.isFile()) {
                obj.put("name", file.getName());
                obj.put("children", new ArrayList<>());
                children.add(obj);
            } else if (file.isDirectory()) {
                children.add(renderFolder(file));
            }
        }
        renderedFolder.put("children", children);
        return renderedFolder;
    }
}
