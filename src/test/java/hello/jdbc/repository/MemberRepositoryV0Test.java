package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member memberV0 = new Member("memberV3", 10000);
        repositoryV0.save(memberV0);

        //findById
        Member findMember = repositoryV0.findById(memberV0.getMemberId());
        log.info("findMember = {}", findMember);
        Assertions.assertThat(findMember).isEqualTo(memberV0);

        //update : money 10000-> 20000
        repositoryV0.update(memberV0.getMemberId(), 20000);
        Member updatedMember = repositoryV0.findById(memberV0.getMemberId());

        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);

        //delete
        repositoryV0.delete(memberV0.getMemberId());
        Assertions.assertThatThrownBy(() -> repositoryV0.findById(memberV0.getMemberId())).isInstanceOf(NoSuchElementException.class);

    }

}