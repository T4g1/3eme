using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace ReflexShooter
{
    public class Gentil : AnimatedSprite
    {
        public Gentil(Canvas device)
            : base(device)
        {
            this.Killable = true;
            this.Mechant = false;
        }

        public void Load(double x, double y)
        {
            X = y;
            Y = x;

            Width = 100;
            Height = 100;

            Texture = Texture2D.FromFile("Content/gentil.png");

            base.Load();
        }

        public override void  Update(GameClock clock)
        {
            base.Update(clock);
        }
    }
}
