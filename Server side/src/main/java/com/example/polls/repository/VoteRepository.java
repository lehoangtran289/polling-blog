package com.example.polls.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.polls.model.ChoiceVoteCount;
import com.example.polls.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
	// # of votes of a choice in a poll (in list of poll)
	@Query("SELECT NEW com.example.polls.model.ChoiceVoteCount(v.choice.id, count(v.id)) "
			+ "FROM Vote v "
			+ "WHERE v.poll.id in :pollIds GROUP BY v.choice.id")
	List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollIds") List<Long> pollIds);
	
	// # of votes of a choice in a poll
	@Query("SELECT NEW com.example.polls.model.ChoiceVoteCount(v.choice.id, count(v.id)) " 
			+ "FROM Vote v "
			+ "WHERE v.poll.id = :pollId GROUP BY v.choice.id")
	List<ChoiceVoteCount> countByPollIdGroupByChoiceId(@Param("pollId") Long pollId);
	
	// information of an user's vote in a Poll (in list of poll)
	@Query("SELECT v from Vote v where v.user.id = :userId and v.poll.id in :pollIds")
	List<Vote> findByUserIdAndPollIdIn(@Param("userId") Long userId, @Param("pollIds") List<Long> pollIds);
	
	// information of an user's vote in a poll
	@Query("SELECT v from Vote v where v.user.id = :userId and v.poll.id = :pollId")
	Vote findByUserIdAndPollId(@Param("userId") Long userId, @Param("pollId") Long pollId);

	// how many vote from an user
	@Query("SELECT COUNT(v.id) from Vote v where v.user.id = :userId")
	long CountByUserId(@Param("userId") Long userId);

	// all polls that an user voted
	@Query("SELECT v.poll.id from Vote v where v.user.id = :userId")
	Page<Long> findVotedPollIdsByUserId(@Param("userId") Long userId, Pageable pageable);

}
