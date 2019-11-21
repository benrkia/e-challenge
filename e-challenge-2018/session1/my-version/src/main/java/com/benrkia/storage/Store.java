package com.benrkia.storage;


import java.util.ArrayList;
import java.util.List;

public class Store {

    private String name;
    private List<Document> documents;

    public Store(String name) {
        this.name = name;
        documents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void empty() {
        this.documents.clear();
    }

    @Override
    public String toString() {

        StringBuilder listStore = new StringBuilder();

        listStore.append(name+":");

        if(documents.size() == 0) {
            listStore.append("empty");
            return listStore.toString();
        }

        for(int i=0;i<documents.size();++i) {
            Document document = documents.get(i);

            listStore.append(document.getName());
            if(i != documents.size()-1) {
                listStore.append(", ");
            }
        }

        return listStore.toString();
    }
}
