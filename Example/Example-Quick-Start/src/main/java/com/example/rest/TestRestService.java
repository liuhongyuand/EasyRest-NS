package com.example.rest;

import com.example.InfoMation;
import tech.dbgsoftware.easyrest.annotations.method.BindURL;
import tech.dbgsoftware.easyrest.annotations.method.Get;
import tech.dbgsoftware.easyrest.annotations.method.Post;
import tech.dbgsoftware.easyrest.annotations.method.SkipCustomerInject;
import tech.dbgsoftware.easyrest.annotations.parameter.AllDefined;
import tech.dbgsoftware.easyrest.aop.customer.CustomInjection;
import tech.dbgsoftware.easyrest.aop.customer.CustomResponse;
import tech.dbgsoftware.easyrest.exception.PermissionException;
import tech.dbgsoftware.easyrest.model.HttpEntity;
import tech.dbgsoftware.easyrest.model.ResponseEntity;
import tech.dbgsoftware.easyrest.utils.JsonTranslationUtil;

@BindURL("/test")
public interface TestRestService extends CustomInjection, CustomResponse {

    @Get
    @SkipCustomerInject
    String ping();

    @Post
    @AllDefined
    String test(String a, String b);

    @Override
    default HttpEntity preCheck(HttpEntity httpEntity) {
        throw new PermissionException("No permission for this rest api.");
    }

    @Override
    default ResponseEntity processResponse(HttpEntity httpEntity, Object response) {
        return ResponseEntity.buildBaseResponse(InfoMation.encryptBasedDes(JsonTranslationUtil.toJsonString(response)));
    }

}
