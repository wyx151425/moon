package com.rumofuture.moon.controller;

import com.rumofuture.moon.domain.Member;
import com.rumofuture.moon.domain.Notification;
import com.rumofuture.moon.domain.User;
import com.rumofuture.moon.domain.schema.NotificationSchema;
import com.rumofuture.moon.service.MemberService;
import com.rumofuture.moon.service.NotificationService;
import com.rumofuture.moon.service.UserService;
import com.rumofuture.moon.util.DataValidationUtil;
import com.rumofuture.moon.util.constant.KeyConstant;
import com.rumofuture.moon.util.dto.Response;
import com.rumofuture.moon.util.dto.ResponseMember;
import com.rumofuture.moon.util.dto.ResponseMemberList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@RestController
@RequestMapping("/member")
public class MemberController {

    private final UserService userService;
    private final MemberService memberService;
    private final NotificationService notificationService;

    @Autowired
    public MemberController(UserService userService, MemberService memberService, NotificationService notificationService) {
        this.userService = userService;
        this.memberService = memberService;
        this.notificationService = notificationService;
    }


    @PostMapping(value = "/invite")
    public Response saveMember(@RequestBody Member member) {
        if (DataValidationUtil.isNullObject(member) || DataValidationUtil.isNullObject(member.getLeader()) ||
                DataValidationUtil.isNullObject(member.getLeader().getId()) ||
                DataValidationUtil.isEmptyStringList(member.getName(), member.getMobilePhoneNumber(), member.getLeader().getName())) {
            return new Response(false, KeyConstant.DATA_TRANSMISSION_ERROR);
        }

        if (!DataValidationUtil.isMobilePhoneNumber(member.getMobilePhoneNumber())) {
            return new Response(false, KeyConstant.USER_MOBILE_PHONE_NUMBER_FORMAT_ERROR);
        }

        User targetUser = userService.getUserByMobilePhoneNumber(member.getMobilePhoneNumber());
        if (DataValidationUtil.isNullObject(targetUser)) {
            return new Response(false, KeyConstant.USER_NOT_EXIST);
        }

        List<Notification> notificationList = notificationService.getNotificationList(
                member.getLeader().getId(), targetUser.getId(), NotificationSchema.TypeVal.TEAM_INVITATION);
        if (null != notificationList && 0 != notificationList.size()) {
            return new Response(false, KeyConstant.INVITATION_ALREADY_EXIST);
        }

        String notificationContent = member.getLeader().getName() +
                "邀请您加入他的团队，担任" +
                member.getTeamPosition() +
                "职务。";
        Notification notification = new Notification();
        notification.setNotifier(member.getLeader());
        notification.setTarget(targetUser);
        notification.setChecked(NotificationSchema.IsCheckedVal.CHECKED);
        notification.setType(NotificationSchema.TypeVal.TEAM_INVITATION);
        notification.setContent(notificationContent);

        int id = notificationService.saveNotification(notification);

        if (0 == id) {
            return new Response(false, KeyConstant.INVITATION_SAVE_FAILED);
        }

        return new Response(true, KeyConstant.INVITATION_SAVE_SUCCESS);
    }

    @PostMapping(value = "/delete")
    public ResponseMember deleteMember(@RequestParam("id") Integer id) {
        if (DataValidationUtil.isNullObject(id)) {
            return new ResponseMember(false, KeyConstant.DATA_TRANSMISSION_ERROR, null);
        }

        int result = memberService.deleteMember(id);

        if (1 == result) {
            return new ResponseMember(true, KeyConstant.DELETE_SUCCESS, null);
        } else {
            return new ResponseMember(false, KeyConstant.DELETE_FAILED, null);
        }
    }

    @PostMapping(value = "/info/update")
    public ResponseMember updateMemberInfo(@RequestBody Member member) {
        if (DataValidationUtil.isNullObject(member.getId())) {
            return new ResponseMember(false, KeyConstant.DATA_TRANSMISSION_ERROR, null);
        }

        Member resultMember = memberService.updateMemberInfo(member);
        if (DataValidationUtil.isNullObject(resultMember)) {
            return new ResponseMember(false, KeyConstant.UPDATE_FAILED, null);
        }

        return new ResponseMember(true, KeyConstant.UPDATE_SUCCESS, resultMember);
    }

    @GetMapping(value = "/get")
    public ResponseMember getMember(@RequestParam("id") Integer id) {
        if (DataValidationUtil.isNullObject(id)) {
            return new ResponseMember(false, KeyConstant.DATA_TRANSMISSION_ERROR, null);
        }

        Member member = memberService.getMemberById(id);
        return new ResponseMember(true, "message", member);
    }

    @GetMapping(value = "/list")
    public ResponseMemberList getMemberList(@RequestParam("id") Integer id) {
        if (DataValidationUtil.isNullObject(id)) {
            return new ResponseMemberList(false, KeyConstant.DATA_TRANSMISSION_ERROR, null);
        }

        List<Member> memberList = memberService.getMemberListByLeader(id);
        return new ResponseMemberList(true, "message", memberList);
    }
}
