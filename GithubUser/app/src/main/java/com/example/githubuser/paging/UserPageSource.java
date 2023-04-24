package com.example.githubuser.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.githubuser.api.GithubApiProvider;
import com.example.githubuser.model.User;
import com.example.githubuser.model.UserResults;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserPageSource extends RxPagingSource<Integer, User> {
    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, User> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, User> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, User>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        Integer nextPageNumber = loadParams.getKey();
        if(nextPageNumber == null){
            nextPageNumber = 1;
        }
        Integer loadSize = loadParams.getLoadSize();

        Integer finalNextPageNumber = nextPageNumber;
        return GithubApiProvider.getGithubApi()
                .getAllUsers(nextPageNumber, loadSize, "stackoverflow")
                .subscribeOn(Schedulers.io())
                .map(userResults -> toLoadResult(userResults, finalNextPageNumber))
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, User> toLoadResult(
            @NonNull UserResults response, Integer currentPage) {
        return new LoadResult.Page<>(
                response.users,
                null, // Only paging forward.
                response.hasMore ? currentPage + 1 : null,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }
}
