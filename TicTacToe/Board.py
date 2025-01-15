class Board:
    def __init__(self, size):
        self.size = size
        self.reset()

    def reset(self):
        self.board = [["" for _ in range(self.size)] for _ in range(self.size)]
        self.row_counter = [{"X": 0, "O": 0} for _ in range(self.size)]
        self.col_counter = [{"X": 0, "O": 0} for _ in range(self.size)]
        self.diag_counter = [{"X": 0, "O": 0} for _ in range(2)]

    def place(self, player, r, c):
        marker = player.marker

        if self.board[r][c]:
            raise ValueError("Cell is already occupied")

        self.board[r][c] = marker
        self.row_counter[r][marker] += 1
        self.col_counter[c][marker] += 1

        if r == c:
            self.diag_counter[0][marker] += 1
        if r + c == self.size - 1:
            self.diag_counter[1][marker] += 1

        return (
            self.row_counter[r][marker] == self.size
            or self.col_counter[c][marker] == self.size
            or self.diag_counter[0][marker] == self.size
            or self.diag_counter[1][marker] == self.size
        )
