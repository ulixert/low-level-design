class Game:
    def __init__(self, player1, player2, board):
        self.board = board
        self.player1 = player1
        self.player2 = player2

    def play_game(self):
        curr_turn = 1
        game_done = False

        while not game_done:
            curr_player = self.player1 if curr_turn % 2 == 1 else self.player2
            r = int(input("Write row position of marker: "))
            c = int(input("Write column position of marker: "))

            if self.board.place(curr_player, r, c):
                game_done = True
                print(f"{curr_player.name} wins")
            else:
                curr_turn += 1
