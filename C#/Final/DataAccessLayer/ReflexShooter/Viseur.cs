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
    public class Viseur : AnimatedSprite
    {
        public Viseur(Canvas device)
            : base(device)
        {
            Mechant = false;
            Killable = false;
            IsDead = false;
        }

        public override void Load()
        {
            X = 0;
            Y = 0;
            
            Width = 30;
            Height = 30;

            Texture = Texture2D.FromFile("Content/viseur.png");

            base.Load();
        }

        public override void  Update(GameClock clock)
        {
            this.X = MouseState.X - (Width/2.0);
            this.Y = MouseState.Y - (Height / 2.0);
            
            base.Update(clock);
        }
    }
}
