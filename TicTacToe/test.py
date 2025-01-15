from TicTacToe import Board, Player
from TicTacToe.tictactoe import Game


alice = Player("Alice", "X")
bob = Player("Bob", "O")
board = Board(3)
game = Game(alice, bob, board)

game.play_game()
