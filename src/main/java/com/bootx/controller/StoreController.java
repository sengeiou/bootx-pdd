package com.bootx.controller;

import com.bootx.common.Message;
import com.bootx.common.Pageable;
import com.bootx.controller.admin.BaseController;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.Member;
import com.bootx.entity.Store;
import com.bootx.security.CurrentUser;
import com.bootx.service.MemberService;
import com.bootx.service.StoreService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author black
 */
@RestController("pddStoreController")
@RequestMapping("/pdd/store")
public class StoreController extends BaseController {

    @Resource
    private StoreService storeService;
    @Resource
    private MemberService memberService;

    @PostMapping("/list")
    @JsonView(BaseEntity.ListView.class)
    public Message list(@CurrentUser Member member, HttpServletRequest request, Pageable pageable){
        if(member==null){
            member = memberService.getCurrent(request);
        }
        return Message.success(storeService.findPage(pageable,member));
    }

    @PostMapping("/unbind")
    public Message unbind(@CurrentUser Member member, HttpServletRequest request, Long id){
        if(member==null){
            member = memberService.getCurrent(request);
        }
        if(member==null){
            return Message.error("登录信息已过期，请重新登录");
        }
        Store store = storeService.find(id);
        if(store==null||store.getMember()!=member){
            return Message.error("不存在该店铺");
        }

        return storeService.unbind(store);

    }


}