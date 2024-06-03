package model;

import model.Post;

public class SimilarPost {
    private Post post;
    private double similarity;

    public SimilarPost(Post post, double similarity) {
        this.post = post;
        this.similarity = similarity;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "SimilarPost{" + "post=" + post + ", similarity=" + similarity + '}';
    }
    
}