package saber.com.challengeappproject.data;

public class GitHubRepository {
    public int id;
    public String name;
    public int branchesCount;
    public int forksCount;
    public int starsCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBranchesCount() {
        return branchesCount;
    }

    public void setBranchesCount(int branchesCount) {
        this.branchesCount = branchesCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }
}
