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
    public class GameClock
    {
        private DateTime _reference;

        public TimeSpan Elapsed
        {
            get;
            private set;
        }

        public TimeSpan TotalElapsed
        {
            get;
            set;
        }

        public GameClock()
        {
            this.Reset();
        }

        public void Reset()
        {
            this._reference = DateTime.Now;
            this.Elapsed = TimeSpan.Zero;
            this.TotalElapsed = TimeSpan.Zero;
        }

        public void Step()
        {
            this.Elapsed = DateTime.Now - this._reference;
            this.TotalElapsed += this.Elapsed;

            this._reference = DateTime.Now;
        }
    }
}
