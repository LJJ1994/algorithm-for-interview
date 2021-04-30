package leetcode.system_design;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

// 355. 设计推特

/**
 * 花了快两小时，从设计到思考，一步一步找bug，看似简单，说实话细节还是挺多的。
 * 真无语！┐(ﾟ～ﾟ)┌
 */
public class Twitter {
    /**
     * store user followed relations
     * @param userId
     * @param followedId
     */
    private ConcurrentMap<Integer, Set<Integer>> followStore;

    /**
     * store user post tweets
     * @param userId
     * @param tweets
     */
    private  ConcurrentMap<Integer, LinkedList<Integer>> tweetStore;

    /**
     * store tweet posted time
     * @param tweetId
     * @param count
     */
    private ConcurrentMap<Integer, Integer> countStore;

    // count tweet posted time
    private  AtomicInteger counter;
    /** Initialize your data structure here. */
    public Twitter() {
        followStore = new ConcurrentHashMap<>();
        tweetStore = new ConcurrentHashMap<>();
        counter = new AtomicInteger(0);
        countStore = new ConcurrentHashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        int count = counter.addAndGet(1);
        LinkedList<Integer> tweetList = tweetStore.get(userId);
        if (tweetList == null || tweetList.isEmpty()) {
            tweetList = new LinkedList<>();
        }
        tweetList.addFirst(tweetId);
        tweetStore.put(userId, tweetList);
        countStore.put(tweetId, count);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {

        List<Integer> mergeList = new ArrayList<>();
        LinkedList<Integer> userOwnTweets = tweetStore.get(userId);
        if (userOwnTweets != null && !userOwnTweets.isEmpty()) {
            mergeList.addAll(userOwnTweets);
        }

        Set<Integer> followeeIds = followStore.get(userId);
        if (followeeIds != null && !followeeIds.isEmpty()) {
            for (Integer followeeId : followeeIds) {
                LinkedList<Integer> followeeTweets = tweetStore.get(followeeId);
                if (followeeTweets != null && !followeeTweets.isEmpty()) {
                    mergeList.addAll(followeeTweets);
                }
            }
        }
        List<int[]> sortList = new ArrayList<>();
        for (Integer tweetId : mergeList) {
            Integer count = countStore.get(tweetId);
            sortList.add(new int[]{tweetId, count});
        }

        Collections.sort(sortList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        List<int[]> subList = new ArrayList<>(sortList);
        if (sortList.size() >= 10) {
            subList = new ArrayList<>(sortList.subList(0, 10));
        }
        List<Integer> result = new ArrayList<>();
        for (int[] ints : subList) {
            result.add(ints[0]);
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followeeId == followerId) {
            return;
        }
        Set<Integer> followedSets = followStore.get(followerId);
        if (followedSets != null) {
            followedSets.add(followeeId);
        } else {
            followedSets = new HashSet<>();
            followedSets.add(followeeId);
        }
        followStore.put(followerId, followedSets);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) {
            return;
        }
        Set<Integer> followedSets = followStore.get(followerId);
        if (followedSets == null) {
            return;
        } else {
            if (followedSets.contains(followeeId)) {
                followedSets.remove(followeeId);
            }
            if (followedSets.isEmpty()) {
                followStore.remove(followerId);
            }
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        System.out.println(twitter.getNewsFeed(1));

        // 用户1关注了用户2.
        twitter.follow(1, 2);

        // 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

        // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
        // 推文id6应当在推文id5之前，因为它是在5之后发送的.
        System.out.println(twitter.getNewsFeed(1));

        // 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // 因为用户1已经不再关注用户2.
        System.out.println(twitter.getNewsFeed(1));

    }
}
