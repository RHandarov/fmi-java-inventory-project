package fmi.java.inventory_project.repository;

import fmi.java.inventory_project.model.ClubMember;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ClubMemberRepository {
    private static Map<Integer, ClubMember> CLUB_MEMBER_TABLE = new HashMap<>();

    public void addClubMember(ClubMember member) {
        if (member == null) {
            throw new NullPointerException("Club member shouldn't be null");
        }

        if (CLUB_MEMBER_TABLE.containsKey(member.getId())) {
            throw new IllegalArgumentException(
                    String.format("Club member with id %d already exists", member.getId())
            );
        }

        CLUB_MEMBER_TABLE.put(member.getId(), member);
    }

    public boolean deleteClubMemberById(Integer id) {
        if (!CLUB_MEMBER_TABLE.containsKey(id)) {
            return false;
        }

        CLUB_MEMBER_TABLE.remove(id);
        return true;
    }

    public Optional<ClubMember> getClubMemberById(Integer id) {
        if (!CLUB_MEMBER_TABLE.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(CLUB_MEMBER_TABLE.get(id));
    }

    public List<ClubMember> getAllClubMembers() {
        return CLUB_MEMBER_TABLE.values().stream().toList();
    }
}
