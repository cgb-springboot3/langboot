package com.hkh.ai.chain.vectorstore;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Primary
@AllArgsConstructor
public class VectorStoreWrapper implements VectorStore{

    private final VectorStoreFactory vectorStoreFactory;
    @Override
    public void storeEmbeddings(List<String> chunkList, List<List<Double>> vectorList, String kid, String docId, List<String> fidList) {
        VectorStore vectorStore = vectorStoreFactory.getVectorStore();
        vectorStore.storeEmbeddings(chunkList, vectorList,  kid,  docId, fidList);
    }

    @Override
    public void removeByDocId(String kid, String docId) {
        VectorStore vectorStore = vectorStoreFactory.getVectorStore();
        vectorStore.removeByDocId(kid,docId);
    }

    @Override
    public void removeByKid(String kid) {
        VectorStore vectorStore = vectorStoreFactory.getVectorStore();
        vectorStore.removeByKid(kid);
    }

    @Override
    public List<String> nearest(List<Double> queryVector, String kid) {
        VectorStore vectorStore = vectorStoreFactory.getVectorStore();
        return vectorStore.nearest(queryVector,kid);
    }

    @Override
    public List<String> nearest(String query, String kid) {
        VectorStore vectorStore = vectorStoreFactory.getVectorStore();
        return vectorStore.nearest(query, kid);
    }

    @Override
    public void newSchema(String kid) {
        VectorStore vectorStore = vectorStoreFactory.getVectorStore();
        vectorStore.newSchema(kid);
    }

    @Override
    public void removeByKidAndFid(String kid, String fid) {
        VectorStore vectorStore = vectorStoreFactory.getVectorStore();
        vectorStore.removeByKidAndFid(kid, fid);
    }
}
