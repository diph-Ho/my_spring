package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("vip_o")
    void vip_O(){
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderService.createOrder(memberId, "itemA", 20000);
        //then
        assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }

    @Test
    @DisplayName("vip_x")
    void vip_x(){
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.BASIC);
        memberService.join(member);
        //when
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        //then
        assertThat(order.getDiscountPrice()).isEqualTo(0);

    }

}
