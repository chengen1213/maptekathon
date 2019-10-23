import argparse
import subprocess
import psutil
import time
from subprocess import Popen
import filecmp

class ProcessProfiler:
  def __init__(self,command):
    self.command = command
    self.execution_state = False

  def execute(self):
    self.max_vms_memory = 0
    self.max_rss_memory = 0

    self.t1 = None
    self.t0 = time.time()
    # self.p = subprocess.Popen(self.command,shell=False)
    self.p = Popen(self.command,stdout=subprocess.PIPE,stderr=subprocess.PIPE,universal_newlines=True)
    self.execution_state = True
    # self.output, self.errors = self.p.communicate()

  def run(self):
      try:
        self.execute()
        #poll as often as possible; otherwise the subprocess might
        # "sneak" in some extra memory usage while you aren't looking
        while self.poll():

          time.sleep(.5)
      finally:
        #make sure that we don't leave the process dangling?
        self.close()


  def poll(self):
    if not self.check_execution_state():
      return False

    self.t1 = time.time()

    try:
      pp = psutil.Process(self.p.pid)

      #obtain a list of the subprocess and all its descendants
      descendants = list(pp.children(recursive=True))
      descendants = descendants + [pp]

      rss_memory = 0
      vms_memory = 0

      #calculate and sum up the memory of the subprocess and all its descendants
      for descendant in descendants:
        try:
          mem_info = descendant.memory_info()

          rss_memory += mem_info[0]
          vms_memory += mem_info[1]
        except psutil.NoSuchProcess:
          #sometimes a subprocess descendant will have terminated between the time
          # we obtain a list of descendants, and the time we actually poll this
          # descendant's memory usage.
          pass
      self.max_vms_memory = max(self.max_vms_memory,vms_memory)
      self.max_rss_memory = max(self.max_rss_memory,rss_memory)

    except psutil.NoSuchProcess:
      return self.check_execution_state()


    return self.check_execution_state()


  def is_running(self):
    return psutil.pid_exists(self.p.pid) and self.p.poll() == None
  def check_execution_state(self):
    if not self.execution_state:
      return False
    if self.is_running():
      return True
    self.executation_state = False
    self.t1 = time.time()
    return False

  def close(self,kill=False):

    self.output, self.errors = self.p.communicate()

    try:
      pp = psutil.Process(self.p.pid)
      if kill:
        pp.kill()
      else:
        pp.terminate()
    except psutil.NoSuchProcess:
      pass

parser = argparse.ArgumentParser()
parser.add_argument('language', help='language')
parser.add_argument('program', help='program file name')
parser.add_argument('input', help='input file name')
parser.add_argument('answer', help='answer file name')

args = parser.parse_args()

language = args.language
program = args.program
input = args.input
answer = args.answer

if language == 'java':
    p = ProcessProfiler([language, program, input])
elif language == 'cpp':
    p = ProcessProfiler([program, input])
elif language == 'cs':
    p = ProcessProfiler([program, input])

p.run()

if p.errors:
    success = False;
    message = p.errors;
    print(success, message)
else:
    success = True;
    result = filecmp.cmp("output.txt", answer)
    message = p.output;
    print(success, result, p.max_vms_memory, p.max_rss_memory,p.t1 - p.t0,  message)

# subprocess.call("java Solution", shell=True)
# out=subprocess.call(["java",'Solution'],stdout=out,stderr=err)
# out=subprocess.call(["java",'Solution'],universal_newlines=True)

# p = Popen(["java",'Solution'],stdout=subprocess.PIPE,stderr=subprocess.PIPE,universal_newlines=True)
# output, errors = p.communicate()
# print(output)
# print(errors)
