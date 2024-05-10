package com.hkh.ai.controller;

import com.hkh.ai.common.ResultData;
import com.hkh.ai.chain.vectorstore.WeaviateVectorStore;
import io.weaviate.client.base.Result;
import io.weaviate.client.v1.misc.model.Meta;
import io.weaviate.client.v1.schema.model.Schema;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * weaviate 向量化
 */
@RestController
@AllArgsConstructor
@RequestMapping("wea")
public class WeaviateController {

    private final WeaviateVectorStore weaviateVectorStore;

    @GetMapping("meta")
    public ResultData<Result<Meta>> meta() {
        Result<Meta> meta = weaviateVectorStore.getMeta();
        return ResultData.success(meta,"查询成功");
    }

    @GetMapping("schemas")
    public ResultData<Result<Schema>> schemas() {
        Result<Schema> schemas = weaviateVectorStore.getSchemas();
        return ResultData.success(schemas,"查询成功");
    }

    @PutMapping("schema/kid")
    public ResultData<Result<Boolean>> schema(@PathVariable(name = "kid") String kid) {
        Result<Boolean> result = weaviateVectorStore.createSchema(kid);
        return ResultData.success(result,"创建成功");
    }

    @DeleteMapping("schema/{kid}")
    public ResultData<Result<Boolean>> delete(@PathVariable(name = "kid") String kid) {
        Result<Boolean> result = weaviateVectorStore.deleteSchema(kid);
        return ResultData.success(result,"删除成功");
    }

}
