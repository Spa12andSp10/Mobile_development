fun main() {
    val sq = Square(4, 3, 10)

    println("До: x=${sq.x}, y=${sq.y}, width=${sq.width}, area=${sq.area()}")

    sq.resize(5)
    println("После resize(5): width=${sq.width}, area=${sq.area()}")

    sq.rotate(RotateDirection.Clockwise, centerX = 3, centerY = -3)
    println("После rotate Clockwise: x=${sq.x}, y=${sq.y}")

    sq.rotate(RotateDirection.CounterClockwise, centerX = 3, centerY = -3)
    println("После rotate CounterClockwise: x=${sq.x}, y=${sq.y}")

    println()

    val c = Circle(0, 0, 10)

    println("До: x=${c.x}, y=${c.y}, radius=${c.radius}, area=${c.area()}")

    c.resize(5)
    println("После resize(5): radius=${c.radius}, area=${c.area()}")

    c.rotate(RotateDirection.Clockwise, centerX = 0, centerY = 0)
    println("После rotate Clockwise вокруг (0,0): x=${c.x}, y=${c.y}")

    c.rotate(RotateDirection.CounterClockwise, centerX = 0, centerY = 0)
    println("После rotate CounterClockwise вокруг (0,0): x=${c.x}, y=${c.y}")

    println()

    val rec = Rect(4, 3, 4, 2)

    println("До: x=${rec.x}, y=${rec.y}, width=${rec.width}, height=${rec.height}, area=${rec.area()}")

    rec.resize(2)
    println("После resize(2):  width=${rec.width}, height=${rec.height}, area=${rec.area()}")

    rec.rotate(RotateDirection.Clockwise, centerX = 3, centerY = -3)
    println("После rotate Clockwise: x=${rec.x}, y=${rec.y}")

    val rec2 = Rect(4, 3, 4, 2)

    rec2.rotate(RotateDirection.CounterClockwise, centerX = 3, centerY = -3)
    println("После rotate CounterClockwise: x=${rec2.x}, y=${rec2.y}")
}