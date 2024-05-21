package com.ricecoffee.game;

public class Score {

	private int playScore;
	private int topScore;
	
	// 함수 초기화
	public Score() {
		this.playScore = 0;
		this.topScore = 0;
	}
	
	// 세션 초기화 - 매 게임 시작마다 호출 요망
	public void resetScore() {
		this.playScore = 0;
	}
	
	// 점수 합산 및 최고점수 기록
	// 매 블럭 합산시마다 상응하는 score.addScore(합쳐진 블럭의 숫자*갯수 아님*)로 점수 기입
	// score.getPlayScore(),score.getTopScore로 현재점수 및 최고점수 갱신 요망
	
	public int addScore(int score) {
		playScore += score;
		if(playScore > topScore) {
			topScore = playScore;
		}
		return playScore;
	}
	
	public int getPlayScore() {
		return playScore;
	}
	
	public int getTopScore() {
		return topScore;
	}
}

